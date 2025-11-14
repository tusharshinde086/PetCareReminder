<%@ page import="java.util.*, com.petcare.model.Activity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pet Care Reminder Dashboard</title>
    <link rel="stylesheet" href="assets/style.css">

    <script>
        // Check reminders every minute
        function checkReminders() {
            const rows = document.querySelectorAll(".activity-row");
            const now = new Date();
            const currentTime = now.getHours().toString().padStart(2, '0') + ":" +
                                now.getMinutes().toString().padStart(2, '0');

            rows.forEach(row => {
                const time = row.dataset.time;
                const name = row.dataset.name;
                const status = row.dataset.status;

                if (status === "Pending" && time === currentTime) {
                    alert("Reminder: Time for activity — " + name);
                }
            });
        }

        setInterval(checkReminders, 60000); // 1 minute
    </script>
</head>

<body>

<header>
    <h1>🐾 Pet Care Reminder Dashboard</h1>
</header>

<div class="nav-links">
    <a href="add_activity.jsp">➕ Add New Activity</a>
</div>

<hr>

<%
    List<Activity> activities = (List<Activity>) request.getAttribute("activities");
%>

<h2>All Activities</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Activity</th>
        <th>Time</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>

    <%
        if (activities != null && !activities.isEmpty()) {
            for (Activity a : activities) {
    %>

    <tr class="activity-row"
        data-time="<%= a.getActivityTime() %>"
        data-name="<%= a.getActivityName() %>"
        data-status="<%= a.getStatus() %>">

        <td><%= a.getActivityId() %></td>
        <td><%= a.getActivityName() %></td>
        <td><%= a.getActivityTime() %></td>
        <td><%= a.getStatus() %></td>

        <td>
            <form action="UpdateActivityStatusServlet" method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= a.getActivityId() %>">

                <select name="status">
                    <option value="Pending" <%= a.getStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                    <option value="Done" <%= a.getStatus().equals("Done") ? "selected" : "" %>>Done</option>
                    <option value="Overdue" <%= a.getStatus().equals("Overdue") ? "selected" : "" %>>Overdue</option>
                </select>

                <button type="submit">Update</button>
            </form>

            <a href="DeleteActivityServlet?id=<%= a.getActivityId() %>"
               onclick="return confirm('Delete this activity?')">Delete</a>
        </td>

    </tr>

    <%  } } else { %>
        <tr>
            <td colspan="5">No activities available.</td>
        </tr>
    <% } %>

</table>

</body>
</html>
