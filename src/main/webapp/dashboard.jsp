<%@ page import="java.util.*,com.petcare.dao.PetDAO,com.petcare.dao.ActivityDAO,com.petcare.model.Pet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<Pet> pets = PetDAO.getAllPets();
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>PetCare Dashboard</title>
</head>
<body>
  <h2>PetCare Dashboard</h2>
  <a href="add_pet.jsp">Add Pet</a> | <a href="add_activity.jsp">Add Activity</a>
  <hr>
  <h3>Pets</h3>
  <table border="1">
    <tr><th>Name</th><th>Type</th><th>Breed</th><th>Age</th><th>Gender</th></tr>
    <%
      for (Pet p : pets) {
    %>
    <tr>
      <td><%=p.getPetName()%></td>
      <td><%=p.getType()%></td>
      <td><%=p.getBreed()%></td>
      <td><%=p.getAge()%></td>
      <td><%=p.getGender()%></td>
    </tr>
    <% } %>
  </table>

  <hr>
  <h3>Activities</h3>
  <!-- Basic activities table fetched server-side or implement ListActivities servlet -->
  <div id="activitiesArea">
    <!-- Could fill with a servlet include to list activities -->
  </div>

<script>
/* Popup reminder check every 60s, presents action buttons via confirm/prompts.
   Better UX: replace with modal UI or SweetAlert for nicer buttons. */
setInterval(() => {
  fetch('ReminderCheckServlet')
    .then(res => res.json())
    .then(list => {
      list.forEach(reminder => {
        // Build a small custom confirm with multiple choices via prompt
        // We'll show a native confirm for Done or else check overdue
        if (confirm("⏰ Reminder: " + reminder.petName + " - " + reminder.activityName + " at " + reminder.time + "\n\nPress OK to mark DONE, Cancel to mark DUE/OVERDUE.")) {
          updateStatus(reminder.activityId, 'Done');
        } else {
          // check if now > scheduled time then mark Overdue
          let now = new Date();
          let parts = reminder.time.split(':');
          let remT = new Date(); remT.setHours(parseInt(parts[0]), parseInt(parts[1]),0,0);
          if (now > remT) updateStatus(reminder.activityId, 'Overdue');
          else updateStatus(reminder.activityId, 'Due');
        }
      });
    })
    .catch(err => console.log(err));
}, 60000); // 60000 ms = 1 minute

function updateStatus(id,status){
  fetch('UpdateStatusServlet',{
    method:'POST',
    headers: {'Content-Type':'application/x-www-form-urlencoded'},
    body: 'activityId=' + encodeURIComponent(id) + '&status=' + encodeURIComponent(status)
  }).then(r=>r.json().catch(()=>{}));
}
</script>
</body>
</html>
