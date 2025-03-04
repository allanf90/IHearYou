📱 IHearYou – Android Voice Recognition App
IHearYou is an Android mobile application developed using Kotlin and Android Studio. The app recognizes voice commands and responds both audibly and visually. When the user says "Blue" or "Red", the screen color changes accordingly, and a voice response is played.

✨ Features
✔️ Voice Recognition – Detects "Blue" or "Red" commands
✔️ Visual Response – Changes screen background to blue or red
✔️ Audio Feedback – Speaks the response:

🟦 "Here is the blue screen"
🟥 "Here is the red screen"
✔️ Material UI – Uses Material Design 3 for a modern look


🛠 Tech Stack
Kotlin – Main programming language
Android Studio / IntelliJ – Development environment
Jetpack Compose / XML – UI design
Google Speech API – Voice recognition
Material Design 3 – Modern UI elements

🚀 Installation & Setup
1️⃣ Clone the Repository

git clone https://github.com/allanf90/IHearYou.git
cd IHearYou

2️⃣ Open in Android Studio
Open Android Studio
Click Open an Existing Project
Select the IHearYou folder
3️⃣ Sync Dependencies
Ensure you have the latest dependencies in build.gradle:
gradle
Copy
Edit
dependencies {
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.core:core-ktx:1.12.0'
}
Click "Sync Now" at the top of Android Studio

4️⃣ Run the App
Connect an Android device or use an emulator
Click Run ▶️ in Android Studio
🔑 Permissions Required
For speech recognition to work, add these permissions in AndroidManifest.xml:


<uses-permission android:name="android.permission.RECORD_AUDIO"/>
<uses-permission android:name="android.permission.INTERNET"/>

🏗 How It Works
1️⃣ User says "Blue" or "Red"
2️⃣ App recognizes the command
3️⃣ Changes screen background color
4️⃣ Speaks the corresponding response

❌ Troubleshooting
🔹 App crashes on launch?

Ensure your themes.xml is using Material 3:

<style name="Theme.IHearYou" parent="Theme.Material3.Light">
🔹 Voice recognition not working?

Check if microphone permission is granted in device settings
🔹 Gradle sync failed?

Try File > Invalidate Caches & Restart
👨‍💻 Contributing
Fork the repo
Create a feature branch (git checkout -b feature-name)
Commit changes (git commit -m "Added new feature")
Push to GitHub (git push origin feature-name)
Submit a Pull Request 🎉

