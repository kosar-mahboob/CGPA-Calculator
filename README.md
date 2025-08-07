# 📊 CGPA & SGPA Calculator – `cgpa-data`

This project is designed to help students manage their academic records efficiently by calculating **CGPA** (Cumulative Grade Point Average) and **SGPA** (Semester Grade Point Average) through a structured application.

## 🔧 Project Structure

This repository contains four core components:

- **`cgpa-applications`** – Main application logic for interacting with user inputs and results.
- **`cgpa-calculators`** – Contains the core calculation logic for CGPA and SGPA.
- **`semester`** – Manages data for individual semesters, such as semester number and total credits.
- **`course`** – Handles course details, including course name, grade, and credit hours.

## 🎯 Features

- Add and manage multiple semesters.
- Add and manage individual courses within each semester.
- Calculate SGPA for each semester.
- Automatically compute overall CGPA.
- Modular structure for easy integration or expansion.


## 🧪 Example Use Case

1. The student enters course data (grade & credits) for each semester.
2. The system calculates SGPA per semester.
3. Once all semester data is entered, the final CGPA is displayed.

## 🚀 Getting Started

Clone the repository:

```bash
git clone https://github.com/yourusername/cgpa-data.git


cgpa-data/
│
├── cgpa-applications/      # Main logic & user flow
├── cgpa-calculators/       # GPA calculation algorithms
├── semester/               # Semester models and logic
└── course/                 # Course models and management
