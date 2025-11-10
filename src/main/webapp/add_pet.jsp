<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Pet</title>
    <link rel="stylesheet" href="assets/style.css">
</head>
<body>
<h2>Add New Pet</h2>

<form action="AddPetServlet" method="post">
    <label>Pet Name:</label>
    <input type="text" name="name" placeholder="Enter pet name" required><br><br>

    <label>Type:</label>
    <select name="type" required>
        <option value="">--Select Type--</option>
        <option value="Dog">Dog</option>
        <option value="Cat">Cat</option>
        <option value="Bird">Bird</option>
        <option value="Fish">Fish</option>
        <option value="Other">Other</option>
    </select><br><br>

    <label>Age (in years):</label>
    <input type="number" name="age" min="0" placeholder="e.g. 3" required><br><br>

    <label>Owner Name:</label>
    <input type="text" name="owner_name" placeholder="Enter owner name" required><br><br>

    <input type="submit" value="Add Pet">
    <a href="DashboardServlet">Back to Dashboard</a>
</form>

</body>
</html>
