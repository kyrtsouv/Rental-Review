package api;

import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.event.*;

import java.awt.event.*;

import gui.*;

public class Controller {
    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    MainGUI mainGUI;

    User user;
    Data data;

    public Controller() {

        data = Data.load();

        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        mainGUI = new MainGUI(loginPanel, registerPanel);

        setWindowListener();

        setLoginListener();
        setRegisterListener();
    }

    public void setLoginListener() {
        loginPanel.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginPanel.getUsername();
                String password = loginPanel.getPassword();
                if ((user = data.getUser(username, password)) == null) {
                    loginPanel.setError("Wrong username or password");
                } else if (user instanceof Renter) {
                    buildRenterPanel();
                } else if (user instanceof Tenant) {
                    buildTenantPanel();
                }
            }
        });
    }

    public void setRegisterListener() {
        registerPanel.addRegisterListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = registerPanel.getName();
                String surname = registerPanel.getSurname();
                String username = registerPanel.getUsername();
                String password = registerPanel.getPassword();
                String type = registerPanel.getType();
                if (name.equals("") || surname.equals("") || username.equals("") || password.equals("")) {
                    registerPanel.setError("Please fill all fields");
                } else if (!data.available(username)) {
                    registerPanel.setError("Username already exists");
                } else if (type.equals("Renter")) {
                    user = new Renter(name, surname, username, password);
                    data.addUser(user);
                    buildRenterPanel();
                } else if (type.equals("Tenant")) {
                    user = new Tenant(name, surname, username, password);
                    data.addUser(user);
                    buildTenantPanel();
                }
            }
        });
    }

    private void buildRenterPanel() {
        Renter renter = (Renter) user;
        RenterPanel renterPanel = new RenterPanel(renter.getTotalReviews(), renter.getRating(),
                buildBasicPreviews(renter.getRentals()));
        setAddRentalListener(renterPanel);
        setLogoutListener(renterPanel);

        mainGUI.showRenter(renterPanel);
    }

    private void buildTenantPanel() {
        SearchPanel searchPanel = new SearchPanel(buildBasicPreviews(data.getRentals()), data.getAmenities());
        setSearchListener(searchPanel);
        TenantDashboardPanel tenantDashboardPanel = new TenantDashboardPanel(((Tenant) user).getRating(),
                buildPreviewsForTenant());
        setLogoutListener(tenantDashboardPanel);
        TenantPanel tenantPanel = new TenantPanel(tenantDashboardPanel, searchPanel);

        mainGUI.showTenant(tenantPanel);
    }

    private HashSet<PreviewPanel> buildPreviewsForTenant() {
        HashSet<PreviewPanel> previews = new HashSet<>();
        HashMap<Rental, Review> reviews = ((Tenant) user).getReviews();
        for (Rental rental : reviews.keySet()) {
            PreviewPanel previewPanel = new PreviewPanel(rental.getName(), rental.getType(), rental.getLocation(),
                    reviews.get(rental).getRating());
            setPreviewListener(previewPanel, rental);
            previews.add(previewPanel);
        }
        return previews;
    }

    private HashSet<PreviewPanel> buildBasicPreviews(HashSet<Rental> rentals) {
        HashSet<PreviewPanel> previews = new HashSet<>();
        for (Rental rental : rentals) {
            PreviewPanel previewPanel = new PreviewPanel(rental.getName(), rental.getType(), rental.getLocation(),
                    rental.getRating());
            setPreviewListener(previewPanel, rental);
            previews.add(previewPanel);
        }
        return previews;
    }

    public void setAddRentalListener(RenterPanel renterPanel) {
        renterPanel.addRentalListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRentalDialog addRentalDialog = new AddRentalDialog(mainGUI, true, data.getAmenities(),
                        data.getRentalTypes());
                addRentalDialog.addSubmitListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = addRentalDialog.getName();
                        String type = addRentalDialog.getRentalType();
                        String address = addRentalDialog.getAddress();
                        String city = addRentalDialog.getCity();
                        String zipcode = addRentalDialog.getZipcode();
                        String description = addRentalDialog.getDescription();
                        HashSet<String> amenities = addRentalDialog.getAmenities();
                        if (name.equals("") || address.equals("") || city.equals("") ||
                                zipcode.equals("")
                                || description.equals("")) {
                            addRentalDialog.setError("All fields with * are required");
                            return;
                        }
                        Rental rental = new Rental(name, type, address, city, zipcode, description,
                                amenities,
                                (Renter) user);
                        ((Renter) user).addRental(rental);
                        data.addRental(rental);
                        PreviewPanel previewPanel = new PreviewPanel(rental.getName(), rental.getType(),
                                rental.getLocation(), rental.getRating());
                        setPreviewListener(previewPanel, rental);
                        renterPanel.addPreview(previewPanel);
                        addRentalDialog.dispose();
                    }
                });
                addRentalDialog.setVisible(true);
            }
        });
    }

    private void setPreviewListener(PreviewPanel previewPanel, Rental rental) {
        previewPanel.addPreviewListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashMap<String, ReviewPanel> reviews = new HashMap<>();
                RentalDialog rentalDialog = new RentalDialog(mainGUI, true, rental.getName(), rental.getType(),
                        rental.getLocation(), rental.getDescription(), rental.getAmenities(), rental.getTotalReviews(),
                        rental.getRating(), reviews, user.getUsername(),
                        rental.getOwner().getUsername());

                for (Review review : rental.getReviews().values()) {
                    ReviewPanel reviewPanel = new ReviewPanel(review.getTenant().getUsername(), review.getRating(),
                            review.getComment(), review.getDate(), user.getUsername(),
                            review.getTenant().getUsername());
                    setEditReviewListener(reviewPanel, rentalDialog, rental);
                    setDeleteReviewListener(reviewPanel, rentalDialog, rental);
                    reviews.put(review.getTenant().getUsername(), reviewPanel);
                }

                rentalDialog.addReviews(reviews);

                setAddReviewListener(rental, rentalDialog);
                setEditRentalListener(previewPanel, rental, rentalDialog);
                setDeleteRentalListener(rental, rentalDialog);
                rentalDialog.setVisible(true);
            }
        });
    }

    private void setAddReviewListener(Rental rental, RentalDialog rentalDialog) {
        rentalDialog.addAddReviewListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddReviewDialog addReviewDialog = new AddReviewDialog(mainGUI, true);
                addReviewDialog.addSubmitListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        float rating = addReviewDialog.getRating();
                        String comment = addReviewDialog.getComment();
                        if (comment.equals("")) {
                            addReviewDialog.setError("Comment is required");
                            return;
                        }
                        Review review = new Review(rating, comment, SDate.dateToString(), (Tenant) user);

                        rental.addReview(review);
                        ((Tenant) user).addReview(rental, review);

                        rental.updateRating();
                        ((Tenant) user).updateRating();
                        rental.getOwner().updateRating();

                        ReviewPanel reviewPanel = new ReviewPanel(user.getUsername(), rating, comment,
                                review.getDate(), user.getUsername(), user.getUsername());

                        setEditReviewListener(reviewPanel, rentalDialog, rental);
                        setDeleteReviewListener(reviewPanel, rentalDialog, rental);

                        rentalDialog.addReview(reviewPanel);

                        buildTenantPanel();
                        addReviewDialog.dispose();
                    }
                });
                addReviewDialog.setVisible(true);
            }
        });
    }

    private void setEditRentalListener(PreviewPanel previewPanel, Rental rental, RentalDialog rentalDialog) {
        rentalDialog.addEditRentalListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditRentalDialog editRentalDialog = new EditRentalDialog(mainGUI, true, data.getAmenities(),
                        data.getRentalTypes());
                editRentalDialog.addSubmitListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = editRentalDialog.getName();
                        String type = editRentalDialog.getRentalType();
                        String address = editRentalDialog.getAddress();
                        String city = editRentalDialog.getCity();
                        String zipcode = editRentalDialog.getZipcode();
                        String description = editRentalDialog.getDescription();
                        HashSet<String> amenities = editRentalDialog.getAmenities();
                        if (name.equals("") || type.equals("") || address.equals("") || city.equals("")
                                || zipcode.equals("") || description.equals("")) {
                            editRentalDialog.setError("All fields with * are required");
                            return;
                        }
                        rental.setName(name);
                        rental.setType(type);
                        rental.setAddress(address);
                        rental.setCity(city);
                        rental.setZipcode(zipcode);
                        rental.setDescription(description);
                        rental.setAmenities(amenities);

                        previewPanel.updatePreview(name, type, description, rental.getRating());
                        rentalDialog.updateRentalDialog(name, type, address + " - " + city + " - " + zipcode,
                                description,
                                amenities);
                        editRentalDialog.dispose();
                    }
                });
                editRentalDialog.setVisible(true);
            }
        });
    }

    private void setDeleteRentalListener(Rental rental, RentalDialog rentalDialog) {
        rentalDialog.addDeleteRentalListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(rentalDialog, "Are you sure?", "Delete Rental",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    ((Renter) user).removeRental(rental);
                    data.removeRental(rental);
                    for (Tenant tenant : rental.getReviews().keySet()) {
                        tenant.removeReview(rental);
                    }
                    buildRenterPanel();
                    rentalDialog.dispose();
                }

            }
        });
    }

    private void setEditReviewListener(ReviewPanel reviewPanel, RentalDialog rentalDialog, Rental rental) {
        reviewPanel.addEditButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditReviewDialog editReviewDialog = new EditReviewDialog(mainGUI, true);
                editReviewDialog.addSubmitListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        float rating = editReviewDialog.getRating();
                        String comment = editReviewDialog.getComment();
                        if (comment.equals("")) {
                            editReviewDialog.setError("Comment is required");
                            return;
                        }

                        Review review = rental.getReviews().get(user);
                        review.updateReview(rating, comment, SDate.dateToString());

                        ((Tenant) user).updateRating();
                        rental.updateRating();
                        rental.getOwner().updateRating();

                        rentalDialog.removeReview(user.getUsername());
                        ReviewPanel reviewPanel = new ReviewPanel(user.getUsername(), rating, comment,
                                review.getDate(), user.getUsername(), user.getUsername());
                        setEditReviewListener(reviewPanel, rentalDialog, rental);
                        setDeleteReviewListener(reviewPanel, rentalDialog, rental);

                        rentalDialog.addReview(reviewPanel);

                        buildTenantPanel();
                        editReviewDialog.dispose();
                    }
                });
                editReviewDialog.setVisible(true);
            }
        });
    }

    private void setDeleteReviewListener(ReviewPanel reviewPanel, RentalDialog rentalDialog, Rental rental) {
        reviewPanel.addDeleteButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Review review = rental.getReviews().get(user);
                rental.removeReview(review);
                ((Tenant) user).removeReview(rental);

                ((Tenant) user).updateRating();
                rental.updateRating();
                rental.getOwner().updateRating();
                rentalDialog.removeReview(user.getUsername());

                buildTenantPanel();
            }
        });
    }

    private void setWindowListener() {
        mainGUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                data.save();
            }
        });
    }

    private void setSearchListener(SearchPanel searchPanel) {
        searchPanel.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchAction(searchPanel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchAction(searchPanel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchAction(searchPanel);
            }
        });

        searchPanel.addSearchListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchAction(searchPanel);
            }
        });
    }

    private void searchAction(SearchPanel searchPanel) {
        String[] searchTextFilters = searchPanel.getSearchText().toLowerCase().split(" ");
        String[] amenitiesFilters = searchPanel.getAmenities().toLowerCase().split(",");

        HashSet<Rental> searchResults = new HashSet<>();

        for (Rental rental : data.getRentals()) {
            boolean stillSearching = true;

            for (String searchTextFilter : searchTextFilters) {
                if (!rental.getSearchID().contains(searchTextFilter)) {
                    stillSearching = false;
                    break;
                }
            }

            if (stillSearching) {
                for (String amenitiesFilter : amenitiesFilters) {
                    if (!rental.getAmenitiesString().contains(amenitiesFilter)) {
                        stillSearching = false;
                        break;
                    }
                }
                if (stillSearching)
                    searchResults.add(rental);
            }
        }

        searchPanel.updateSearchResults(buildBasicPreviews(searchResults));
    }

    private void setLogoutListener(RenterPanel renterPanel) {
        renterPanel.addLogoutListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = null;
                mainGUI.showLogin();
            }
        });
    }

    private void setLogoutListener(TenantDashboardPanel tenantDashboardPanel) {
        tenantDashboardPanel.addLogoutListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user = null;
                mainGUI.showLogin();
            }
        });
    }
}
