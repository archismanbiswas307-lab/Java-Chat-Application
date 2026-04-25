# Chatting Application

A Java Swing-based real-time peer-to-peer chatting application with a WhatsApp-inspired UI.

## Features

- **Real-time Messaging**: Send and receive messages instantly between two peers
- **Circular Profile Icons**: Smooth antialiased circular icons for user profiles
- **Modern UI**: WhatsApp-inspired dark green header with white message bubbles
- **Undecorated Window**: Frameless window design with drag-to-move functionality
- **Auto-scrolling**: Message history with automatic scrolling for new messages
- **Timestamp**: Each message displays the time it was sent
- **Status Indicator**: Shows user online/active status
- **Clean Interface**: Intuitive design with Send button and message input field

## System Requirements

- Java 8 or higher (tested on Java 25.0.2 LTS)
- Operating System: Windows, macOS, or Linux
- Minimum Screen Resolution: 1024x768
- Icons folder with PNG image files (see Project Structure)

## Project Structure

```
Chatting Application/
├── Server.java              # Server-side chat application
├── Client.java              # Client-side chat application
├── README.md               # This file
└── icons/                  # Icon resources
    ├── 1.png               # Server profile picture
    ├── 2.png               # Client profile picture
    ├── 3.png               # Back/close icon
    ├── 3icon.png           # More options icon
    ├── video.png           # Video call icon
    └── phone.png           # Phone call icon
```

## Installation & Setup

1. **Extract the project** to your desired location
2. **Ensure icons folder** contains all required PNG files
3. **Compile the Java files**:
   ```bash
   cd "path\to\Chatting Application"
   javac Server.java Client.java
   ```

## Usage

### Starting the Server

1. Run the Server application:
   ```bash
   java Server
   ```
2. The server window will appear and wait for client connections
3. Listen on port: **6001**

### Starting the Client

1. In a separate terminal/command window, run the Client:
   ```bash
   java Client
   ```
2. The client will automatically connect to the server at `127.0.0.1:6001`
3. Both windows should now be connected and ready to chat

### Sending Messages

1. Type your message in the text field at the bottom
2. Click the **"Send"** button or press Enter
3. Messages appear in green bubbles on the right (your messages)
4. Received messages appear in white bubbles on the left
5. Each message shows the timestamp when sent

## UI Components

### Header Bar
- **Back Icon**: Click to close the application
- **Profile Icon**: Circular user profile picture
- **User Name**: Displays the name (Gaitonde for Server, Bunty for Client)
- **Status**: Shows "Active Now"
- **Video & Phone Icons**: Call buttons (UI only)
- **More Icon**: Additional options button

### Message Area
- Scrollable chat history
- Color-coded messages (green for sent, white for received)
- Timestamps displayed below each message
- Automatic scrolling for new messages

### Input Area
- Text field for typing messages
- Send button to transmit messages

## Features Explanation

### Circular Icons
Profile and back icons are rendered as smooth circular images using:
- `createCircularIcon()` method with antialiasing
- BufferedImage circular clipping
- AlphaComposite for smooth edges

### Undecorated Window
- Window has no title bar or borders
- Drag the header bar to move the window
- Mouse listeners on the header enable drag functionality

### Message Formatting
- HTML-formatted message bubbles
- Automatic text wrapping at 150px width
- Custom padding and borders for visual appeal
- Timestamps in 24-hour HH:mm format

### Socket Communication
- Uses Java ServerSocket on port 6001
- DataInputStream/DataOutputStream for UTF-8 message transmission
- Try-with-resources for proper resource management
- Non-blocking message receiver in separate thread

## How It Works

1. **Server Start**: 
   - Creates GUI window
   - Opens ServerSocket on port 6001
   - Waits for client connection

2. **Client Start**:
   - Creates GUI window
   - Connects to Server via Socket
   - Establishes input/output streams

3. **Message Flow**:
   - User types message and clicks Send
   - Message displayed locally as green bubble
   - Message sent via DataOutputStream
   - Receiver gets message via DataInputStream
   - Message displayed as white bubble with timestamp

4. **Closing**:
   - Click back icon to exit
   - Closes socket connections
   - All resources are properly released

## Customization

### Change User Names
Edit the JLabel names in constructor:
```java
// In Server.java
JLabel name = new JLabel("Gaitonde");

// In Client.java
JLabel name = new JLabel("Bunty");
```

### Change Port Number
Modify the port in both files:
```java
// In Server.java main()
ServerSocket serverSocket = new ServerSocket(6001);

// In Client.java main()
Socket s = new Socket("127.0.0.1", 6001);
```

### Change IP Address
For remote connections, update Client.java:
```java
Socket s = new Socket("YOUR_SERVER_IP", 6001);
```

### Customize Colors
Change the RGB values in the JPanel or JLabel:
```java
// Header background (Dark Green)
new Color(7, 94, 84)

// Sent message (Green)
new Color(37, 211, 102)

// Received message (White)
Color.WHITE
```

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Icons not showing | Ensure `icons` folder is in the project root with all PNG files |
| Connection refused | Check if Server is running on the correct port (6001) |
| Port already in use | Change the port number in both Server and Client |
| Java version error | Update to Java 8 or higher |
| Application crashes | Ensure both files compiled without errors: `javac *.java` |

## Future Enhancements

- [ ] Group chatting support for multiple users
- [ ] Message persistence (save chat history)
- [ ] User authentication and login system
- [ ] File transfer capability
- [ ] Voice and video call integration
- [ ] Emoji support
- [ ] Message search functionality
- [ ] User presence indicators
- [ ] Network encryption (SSL/TLS)
- [ ] Database integration for message history
- [ ] Mobile app version

## Technical Stack

- **Language**: Java
- **GUI Framework**: Swing
- **Networking**: Sockets (TCP/IP)
- **Data Format**: UTF-8 strings
- **Architecture**: Client-Server (Peer-to-Peer)

## Known Limitations

- Single peer-to-peer connection only (no multi-user support)
- No message history persistence
- No encryption on transmitted data
- Requires manual server startup before client
- Local network only (127.0.0.1)
- Messages only sent as text

## License

This project is open source and available for educational purposes.

## Author

Created as a demonstration of Java Swing and Socket programming for real-time communication.

---

**Last Updated**: April 25, 2026

For questions or improvements, feel free to modify and enhance the code!
