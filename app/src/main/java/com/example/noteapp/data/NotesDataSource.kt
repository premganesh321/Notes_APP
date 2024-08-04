package com.example.noteapp.data

import com.example.noteapp.model.Note

class NotesDataSource {
    fun loadNotes() : List<Note>{
        return listOf(
            Note(title = "Meeting Reminder", description = "Don't forget the team meeting tomorrow at 10 AM in the conference room. Bring your project updates."),
            Note(title = "Grocery List", description = "Milk, eggs, bread, and vegetables. Check the pantry for anything else you might need."),
            Note(title = "Birthday Gift Ideas", description = "Consider a personalized mug, a gift card to their favorite store, or a nice watch."),
            Note(title = "Workout Schedule", description = "Monday - Cardio, Wednesday - Strength Training, Friday - Yoga. Don’t forget to stretch before and after!"),
            Note(title = "Travel Packing List", description = "Passport, travel-size toiletries, comfortable shoes, and charger. Also, check the weather forecast."),
            Note(title = "Book Recommendations", description = "'The Midnight Library' by Matt Haig, 'Circe' by Madeline Miller, and 'Educated' by Tara Westover."),
            Note(title = "To-Do List for the Week", description = "Finish report, call plumber, schedule dentist appointment, and organize files."),
            Note(title = "Recipe for Spaghetti Bolognese", description = "Ground beef, tomato sauce, onions, garlic, and pasta. Cook meat, add sauce, and simmer for 30 minutes."),
            Note(title = "Holiday Gift List", description = "List gifts for family and friends, set budget, and make sure to shop early to avoid last-minute stress."),
            Note(title = "Home Improvement Ideas", description = "Paint living room, replace kitchen faucet, and organize garage. Plan budget and timeline."),
            Note(title = "Daily Meditation Practice", description = "10 minutes of mindfulness each morning. Focus on breathing and being present."),
            Note(title = "Movie Night Choices", description = "Choose between 'Inception,' 'The Matrix,' or 'The Grand Budapest Hotel.' Prepare popcorn!"),
            Note(title = "Weekly Meal Plan", description = "Monday - Chicken stir-fry, Tuesday - Tacos, Wednesday - Veggie curry, and Thursday - Grilled salmon."),
            Note(title = "Client Follow-Up", description = "Send thank-you email, provide project updates, and schedule a call to discuss next steps."),
            Note(title = "Pet Care Routine", description = "Feed twice daily, walk in the morning and evening, and schedule regular vet check-ups."),
            Note(title = "Personal Goals for the Month", description = "Improve fitness level, read one book, learn a new skill, and practice mindfulness daily."),
            Note(title = "Event Planning Checklist", description = "Book venue, send invitations, arrange catering, and prepare a schedule for the day."),
            Note(title = "Plant Care Instructions", description = "Water every 3 days, ensure it gets indirect sunlight, and fertilize once a month."),
            Note(title = "Important Deadlines", description = "Submit grant application by August 15, complete design draft by September 1, and review budget by September 10."),
            Note(title = "Car Maintenance Schedule", description = "Check oil every 3,000 miles, rotate tires every 6,000 miles, and schedule a service check every 12,000 miles.")
        )
    }
}