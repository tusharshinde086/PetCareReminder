<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Activity</title>
    <link rel="stylesheet" href="assets/style.css">
    <script>
        // ✅ Show or hide custom "Other" input box
        function toggleCustomActivity(select) {
            const otherBox = document.getElementById("customActivityBox");
            if (select.value === "Other") {
                otherBox.style.display = "block";
            } else {
                otherBox.style.display = "none";
            }
        }

        // ✅ Form validation before submit
        function validateForm() {
            const petId = document.getElementById("petId").value.trim();
            const activity = document.getElementById("activityName").value;
            const time = document.getElementById("activityTime").value.trim();

            if (petId === "" || isNaN(petId)) {
                alert("Please enter a valid Pet ID.");
                return false;
            }

            if (activity === "") {
                alert("Please select an activity.");
                return false;
            }

            if (time === "") {
                alert("Please enter activity time.");
                return false;
            }

            return true;
        }
    </script>
</head>

<body>
<header>
    <h1>🐾 Add Activity for Pet</h1>
</header>

<div class="container">
    <form action="AddActivityServlet" method="post" onsubmit="return validateForm();">
        <label>Pet ID:</label>
        <input type="number" id="petId" name="pet_id" placeholder="Enter Pet ID" required>

        <label>Activity Name:</label>
        <select id="activityName" name="activity_name" onchange="toggleCustomActivity(this);" required>
            <option value="">--Select--</option>
            <option value="Feed">Feed</option>
            <option value="Walk">Walk</option>
            <option value="Vet Visit">Vet Visit</option>
            <option value="Grooming">Grooming</option>
            <option value="Playtime">Playtime</option>
            <option value="Other">Other</option>
        </select>

        <div id="customActivityBox" style="display:none;">
            <label>Enter Custom Activity:</label>
            <input type="text" name="customActivity" placeholder="Type custom activity name">
        </div>

        <label>Time:</label>
        <input type="time" id="activityTime" name="activity_time" required>

        <label>Status:</label>
        <select name="status">
            <option value="Due">Due</option>
            <option value="Done">Done</option>
            <option value="Overdue">Overdue</option>
        </select>

        <button type="submit">Add Activity</button>
        <a href="dashboard.jsp" class="btn">⬅ Back to Dashboard</a>
    </form>
</div>

<footer>
    <p>© 2025 Pet Care Reminder | MCA Div-B</p>
</footer>

</body>
</html>
