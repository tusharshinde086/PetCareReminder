<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Activity</title>
    <link rel="stylesheet" href="assets/style.css">

    <script>
        function validateForm() {
            const name = document.getElementById("activityName").value.trim();
            const time = document.getElementById("activityTime").value;

            if (name === "") {
                alert("Please enter an activity name.");
                return false;
            }
            if (time === "") {
                alert("Please select activity time.");
                return false;
            }
            return true;
        }
    </script>
</head>

<body>

<header>
    <h1>➕ Add New Activity</h1>
</header>

<div class="container">
    <form action="AddActivityServlet" method="post" onsubmit="return validateForm();">

        <label>Activity Name:</label>
        <input type="text" id="activityName" name="activity_name"
               placeholder="e.g., Feed Pet" required>

        <label>Activity Time:</label>
        <input type="time" id="activityTime" name="activity_time" required>

        <button type="submit">Add Activity</button>
        <a href="DashboardServlet" class="btn">⬅ Back to Dashboard</a>
    </form>
</div>

<footer>
    <p>© 2025 Pet Care Reminder | MCA Div-B</p>
</footer>

</body>
</html>
