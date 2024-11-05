
# Credit Card UI Project

This project is an Android application that provides a custom credit card input interface, designed to improve XML layout skills and practice automated formatting and validation. It features input fields for the card number, expiration date, and cardholder name with built-in formatting and validation rules.

## Features

- **Credit Card Number Formatting**: Automatically formats the card number as the user types, inserting a space every four digits (e.g., `1234 5678 9101 1213`).
- **Expiration Date Field**: Accepts dates in the format `MM/YY`, with automated input control.
- **Input Validations**:
  - Card number must contain exactly 16 digits.
  - Cardholder name must be at least 3 characters long.
  - Expiration date must be in the format `MM/YY`.
- **Custom UI Styling**:
  - Credit card icon displayed at the top.
  - Rounded borders and shadow effects for a realistic card appearance.
  - Improved font styles for readability and usability.

## Screenshots

![image](https://github.com/user-attachments/assets/46b38661-8424-4961-b132-c6c5d901457d)

## Installation

1. Clone the repository:

    git clone https://github.com/yourusername/credit-card-ui.git

2. Open the project in Android Studio.
3. Build and run the application on an emulator or physical device.

## Usage

- Enter a 16-digit card number; spaces are automatically added every four digits.
- Enter the expiration date in `MM/YY` format.
- Type the cardholder's name with a minimum of 3 characters.

## Technologies Used

- **Android Studio** - Development Environment
- **Kotlin** - Programming Language
