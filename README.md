# 🧭 WheresJason

**WheresJason** is a powerful and minimal JavaFX application for **viewing, editing, and navigating JSON files** in a user-friendly tree structure.

![Banner](https://github.com/user-attachments/assets/7c539157-061d-4e57-a840-8d440dec64c0)


---

## 📦 Features

- 📂 Load and display any JSON file as an interactive `TreeView`
- 💾 Save modified JSON files
- ✍️ Edit raw JSON text with **live preview** in the tree
- 🔍 Search and highlight JSON keys using **next/previous match**
- 📜 Auto-save changes to a temporary file as you type
- 📖 Built-in help linking to GitHub documentation

---

## 🚀 Getting Started

### ✅ Prerequisites

- **Java 24+**
- **JavaFX 24**
- [Gson library](https://github.com/google/gson)

---

## 💾 Download

### 🚀 Quick Start (Recommended)

1. **Download WheresJason**
   - Go to the [Releases](https://github.com/topno-dev/WheresJason/releases) page
   - Download the latest `WheresJason.exe` or `WheresJason.jar` file
   - Extract it to your desired location

2. **Download Java 24**
   - Download Java 24 from Oracle: [jdk-24_windows-x64_bin.exe](https://download.oracle.com/java/24/latest/jdk-24_windows-x64_bin.exe)
   - Run the installer and follow the installation wizard

3. **Run WheresJason**
   - Navigate to the folder where you downloaded `WheresJason.exe` or `WheresJason.jar`
   - Double-click `WheresJason.exe` or `WheresJason.jar`
   - Start exploring your JSON files! 🎉

## 🔧 Do it on Your Own

### 1. Clone the Repository

```bash
git clone https://github.com/topno-dev/WheresJason.git
cd WheresJason
```

### 2. Download JavaFX SDK

Download the JavaFX SDK from:
https://gluonhq.com/products/javafx/

Extract it and place it in a known path, for example:
```
D:\java_sdk\javafx-sdk-24.0.1\
```

### 3. Download Gson

Download `gson-2.13.1.jar` and place it at:
```
D:\java_sdk\gson\gson-2.13.1.jar
```

---

## 🛠️ Compilation & Run

### 🧠 IntelliJ IDEA Setup

1. Add JavaFX SDK to your module-path
2. Add Gson JAR to classpath
3. Set VM options:

```bash
--module-path "D:\java_sdk\javafx-sdk-24.0.1\lib" 
--add-modules javafx.controls,javafx.fxml 
--class-path "D:\Code\WheresJason\WheresJason\target\classes;D:\java_sdk\gson\gson-2.13.1.jar"
```

### 💻 Command Line Compilation

```bash
javac --module-path "path\to\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml ^
-cp "lib\gson-2.13.1.jar" ^
-d out src\io\github\clupthegreat\wheresjason\*.java
```

### ▶️ Run the Application

```bash
java --module-path "path\to\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml ^
-cp "out;lib\gson-2.13.1.jar" ^
io.github.clupthegreat.wheresjason.WheresJasonApplication
```

---

## 📸 Screenshots

### 📁 JSON File loaded in Tree View
![image](https://github.com/user-attachments/assets/d4995540-ec22-43bf-82bb-0c904d8a8095)
![image](https://github.com/user-attachments/assets/9665f29b-2ce0-4fc2-907b-d2bedc97001c)


### ✍️ JSON Editing with auto-preview
![image](https://github.com/user-attachments/assets/2898b6ed-7077-4406-a692-171ee35ca80e)


### 🔍 Search functionality
![image](https://github.com/user-attachments/assets/d3dc3c1e-9a05-44ea-96a1-58a81dd8b5c5)


---

## 🧪 Usage Instructions

1. Click **"Open"** to load a `.json` file
2. The raw JSON will appear in the TextArea
3. Edit the JSON — the tree updates automatically
4. Use **"Save"** to export your changes to a file
5. Use **Search** to locate specific keys:
   - Click **Next** or **Previous** to cycle through matches
6. Click **Help** to open the GitHub page for documentation

---

## 🔐 Folder Structure

```
WheresJason/
├── src/
│   └── io/github/clupthegreat/wheresjason/
│       ├── WheresJasonApplication.java
│       ├── WheresJasonController.java
│       ├── TreeViewCreator.java
│       ├── JSONManager.java
│       └── sampledata_simple.json
├── tempFiles/
│   └── temp.json (auto-created on edit)
├── lib/
│   └── gson-2.13.1.jar
└── resources/
    └── wheresjason-view.fxml
```

---

## 🧠 Known Limitations

- JSON must be valid for tree preview to update
- Large JSON files may affect performance
- Currently supports only `.json` file types

---

## 💡 Future Ideas

- JSON schema validation
- Dark mode UI
- Copy/paste or drag/drop nodes
- Multi-tab editor support

---

## 📬 License

MIT License – feel free to use, modify, and share with attribution.

---

## 🔗 GitHub Link

https://github.com/topno-dev/WheresJason
