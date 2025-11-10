<%@ page import="java.util.*,com.petcare.model.Pet,com.petcare.model.Activity" %>
<html>
<head>
    <title>Pet Care Dashboard</title>
    <link rel="stylesheet" href="assets/style.css">
    <script>
        // 🔔 Popup reminder for activities
        function checkReminders() {
            const activities = document.querySelectorAll(".activity-time");
            const now = new Date();
            const currentTime = now.getHours() + ":" + now.getMinutes();

            activities.forEach(act => {
                const scheduled = act.getAttribute("data-time");
                const status = act.getAttribute("data-status");

                if (status === "Pending" && scheduled === currentTime) {
                    alert("Reminder: " + act.getAttribute("data-name") + " for Pet ID " + act.getAttribute("data-petid"));
                }
            });
        }
        // Run every 1 minute
        setInterval(checkReminders, 60000);
    </script>
</head>
<body>
    <h2>Pet Care Reminder Dashboard</h2>

    <div class="nav-links">
        <a href="add_pet.jsp">Add New Pet</a> |
        <a href="add_activity.jsp">Add Activity</a>
    </div>
    <hr>

    <% 
        List<Pet> pets = (List<Pet>) request.getAttribute("pets");
        List<Activity> acts = (List<Activity>) request.getAttribute("activities");
    %>

    <h3>All Pets</h3>
    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>Pet ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Age</th>
            <th>Owner</th>
            <th>Actions</th>
        </tr>
        <% if (pets != null) {
            for (Pet p : pets) { %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getType() %></td>
            <td><%= p.getAge() %></td>
            <td><%= p.getOwnerName() %></td>
            <td>
                <a href="EditPetServlet?id=<%=p.getId()%>">Edit</a> |
                <a href="DeletePetServlet?id=<%=p.getId()%>" onclick="return confirm('Are you sure you want to delete this pet?')">Delete</a>
            </td>
        </tr>
        <% } } else { %>
        <tr><td colspan="6">No pets found.</td></tr>
        <% } %>
    </table>

    <h3>Activities</h3>
    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>Activity ID</th>
            <th>Pet ID</th>
            <th>Activity</th>
            <th>Time</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <% if (acts != null) {
            for (Activity a : acts) { %>
        <tr class="activity-time" 
            data-time="<%= a.getActivityTime() %>" 
            data-name="<%= a.getActivityName() %>" 
            data-status="<%= a.getStatus() %>" 
            data-petid="<%= a.getPetId() %>">
            <td><%= a.getActivityId() %></td>
            <td><%= a.getPetId() %></td>
            <td><%= a.getActivityName() %></td>
            <td><%= a.getActivityTime() %></td>
            <td><%= a.getStatus() %></td>
            <td>
                <form action="UpdateActivityStatusServlet" method="post" style="display:inline;">
                    <input type="hidden" name="activityId" value="<%= a.getActivityId() %>">
                    <select name="status">
                        <option <%= a.getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                        <option <%= a.getStatus().equals("Done") ? "selected" : "" %>>Done</option>
                        <option <%= a.getStatus().equals("Overdue") ? "selected" : "" %>>Overdue</option>
                    </select>
                    <input type="submit" value="Update">
                </form>
            </td>
        </tr>
        <% } } else { %>
        <tr><td colspan="6">No activities found.</td></tr>
        <% } %>
    </table>

</body>
</html>
