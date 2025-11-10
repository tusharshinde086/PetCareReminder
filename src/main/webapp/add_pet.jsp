<!DOCTYPE html>
<html>
<head><meta charset="utf-8"><title>Add Pet</title></head>
<body>
  <h2>Add Pet</h2>
  <form action="AddPetServlet" method="post">
    <label>Name:</label><input type="text" name="pet_name" required><br>
    <label>Type:</label>
    <select name="type">
      <option>Dog</option>
      <option>Cat</option>
      <option>Bird</option>
      <option>Rabbit</option>
      <option value="other">Other...</option>
    </select>
    <input type="text" name="customType" placeholder="Enter custom type" style="display:none;"><br>
    <label>Breed:</label><input type="text" name="breed"><br>
    <label>Age:</label><input type="number" name="age"><br>
    <label>Gender:</label>
    <select name="gender">
      <option>Male</option>
      <option>Female</option>
    </select><br>
    <input type="submit" value="Add Pet">
  </form>

<script>
const sel = document.querySelector('select[name=type]');
const ct = document.querySelector('input[name=customType]');
sel.addEventListener('change', e => {
  ct.style.display = e.target.value === 'other' ? 'inline-block' : 'none';
});
</script>
</body>
</html>
