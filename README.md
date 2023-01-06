# Student Unity
<p align="center">
  <img src="http://user-images.githubusercontent.com/73740339/210889616-9bb354a3-1d8a-43b6-8b22-2932582f8f1c.png" />
</p>

[![code style: prettier](https://img.shields.io/badge/code_style-prettier-ff69b4.svg?style=flat-square)](https://github.com/prettier/prettier)

## Problem
Students often feel overwhelmed by the amount of work they have to do. They find themselves
missing deadlines Or sometimes forgetting to do some assignments. They are distracted
between many different applications like Microsoft teams, google classroom, google drive,
telegram, and WhatsApp. Each one of those applications fulfills a specific necessity, and the
distribution of tasks among many applications makes the process less convenient.

One of the problems caused by having many applications in the same pipeline is the great
amount of disorganization, which causes some assignments to be missed and prioritizing tasks to
be messy.

## Solution
This application provides an all-in-one solution by combining all the needed functionalities from
the many applications in one application elegantly, which make things more organized and easy
to track, prioritize and manage.

## Possible Users of the system
### Primary Users:
- Students: typical users of the system are students in colleges or schools.
- Admins: Admins can be anyone who will be adding the materials, announcements, and
deadlines, admins can be students, teaching assistants, teachers, and professors.
### Secondary Users:
- Colleges and educational institutions will install the system to help their students.

## Technology stack
- Back-End: Java, Spring boot, JPA.
- Front-End: Angular@13, Bootstrap.
- Notification System: FirebaseMessagingService

## Distinctions From Alternatives
- The key distinction is automation other to-do apps need you to write every task by
yourself and specify every small tiny detail, on the other hand, this app is smart and
generates these tasks automatically either from the admin or from the revision system.
- Another important distinction is that this is not just a to-do app, it’s one of the main
functionalities not limited.

## Features
- Show Registered Courses
- Course Page
- General Materials for the course in general
- Specific materials for topics
- Course Plan
- Announcements
- Deadlines and assignment (Task)
- Filter tasks by course, tag, day… etc.
- Sort tasks by time, and course... Etc.
- Mark task as completed or not-completed.
- Notifications are sent when a new task or Announcement was added
- Mute notifications
- Revision System
- Admin Functionalities:
  - Add Tasks
  - Send Announcements
  - Organize Course Materials

## Project Structure
The application is organized as layered architecture: API layer, Logic layer, DAO or Repository layer.

## Usage
You can run the application in development mode as follows:
- Frontend

      ng serve --open
       
- Backend: run the backend server from your favorite IDE
  - make sure that ``MySQL`` is already installed.
  - edit ``username`` and ``password`` in **application.properties** file.
  
