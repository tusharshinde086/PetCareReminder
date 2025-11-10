// ✅ Popup Reminder for Activities
function showReminder(petName, activity, status) {
    let message = `Reminder for ${petName}: ${activity}`;
    if (status === "due") {
        alert(message + "\nStatus: DUE");
    } else if (status === "overdue") {
        alert(message + "\n⚠️ Status: OVERDUE!");
    } else if (status === "done") {
        alert(message + "\n✅ Status: DONE!");
    }
}

// ✅ Confirm Delete
function confirmDelete() {
    return confirm("Are you sure you want to delete this pet?");
}

// ✅ Validate Add/Edit Pet Form
function validatePetForm() {
    const name = document.getElementById("petName").value.trim();
    const type = document.getElementById("petType").value.trim();

    if (name === "" || type === "") {
        alert("Please enter all required pet details.");
        return false;
    }
    return true;
}

// ✅ Show reminder automatically (example data)
window.onload = function() {
    const demoReminders = [
        { pet: "Buddy", activity: "Feeding", status: "done" },
        { pet: "Luna", activity: "Vaccination", status: "due" },
        { pet: "Rocky", activity: "Walk", status: "overdue" }
    ];

    // Trigger reminders one by one with a delay
    demoReminders.forEach((r, index) => {
        setTimeout(() => {
            showReminder(r.pet, r.activity, r.status);
        }, (index + 1) * 2500);
    });
};
