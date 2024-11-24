package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Display all users in an HTML table
    @GetMapping
    public String getAllUsers() {
        List<User> users = userService.getAllUsers();
        return buildHtmlTable(users, "All Users");
    }

    // Search users by username and display them in an HTML table
    @GetMapping("/search")
    public String searchUsers(@RequestParam String username) {
        List<User> users = userService.findByUsername(username);
        return buildHtmlTable(users, "Search Results for Username: " + username);
    }

    // Display the HTML form for adding a new user
    @GetMapping("/add")
    public String getAddUserForm() {
        return "<html>" +
                "<body>" +
                "<h2 style='text-align: center;'>Add a New User</h2>" +
                "<form method='POST' action='/users' style='width: 50%; margin: auto;'>" +
                "Username: <input type='text' name='username' required style='width: 100%; margin: 5px 0;'><br>" +
                "Email: <input type='email' name='email' required style='width: 100%; margin: 5px 0;'><br>" +
                "<button type='submit' style='width: 100%; background-color: #4CAF50; color: white; padding: 10px;'>Add User</button>" +
                "</form>" +
                "<div style='text-align: center; margin-top: 10px;'><a href='/users'>Go back to User List</a></div>" +
                "</body>" +
                "</html>";
    }

    // Add a new user and display a success message
    @PostMapping
    public String addUser(@RequestParam String username, @RequestParam String email) {
        userService.addUser(new User(null, username, email));
        return "<html><body>" +
                "<h3 style='text-align: center;'>User added successfully!</h3>" +
                "<div style='text-align: center;'><a href='/users'>Go back to User List</a></div>" +
                "</body></html>";
    }

    // Helper method to build the HTML table
    private String buildHtmlTable(List<User> users, String header) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<title>").append(header).append("</title>");
        htmlBuilder.append("<style>");
        htmlBuilder.append("table { border-collapse: collapse; width: 50%; margin: auto; }");
        htmlBuilder.append("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
        htmlBuilder.append("th { background-color: #f2f2f2; }");
        htmlBuilder.append("</style>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");
        htmlBuilder.append("<h2 style='text-align: center;'>").append(header).append("</h2>");
        htmlBuilder.append("<table>");
        htmlBuilder.append("<thead>");
        htmlBuilder.append("<tr><th>ID</th><th>Username</th><th>Email</th></tr>");
        htmlBuilder.append("</thead>");
        htmlBuilder.append("<tbody>");

        for (User user : users) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(user.getId()).append("</td>");
            htmlBuilder.append("<td>").append(user.getUsername()).append("</td>");
            htmlBuilder.append("<td>").append(user.getEmail()).append("</td>");
            htmlBuilder.append("</tr>");
        }

        htmlBuilder.append("</tbody>");
        htmlBuilder.append("</table>");
        htmlBuilder.append("<br>");
        htmlBuilder.append("<div style='text-align: center;'><a href=\"/users\">Go back to User List</a></div>");
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }
}
