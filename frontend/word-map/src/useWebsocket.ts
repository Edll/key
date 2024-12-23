export const useWebsocket = () => {
    const socket = new WebSocket("ws://localhost:18080/wordmap")

// Connection opened
    socket.addEventListener("open", event => {
        socket.send("Connection established")
    });

// Listen for messages
    socket.addEventListener("message", event => {
        console.log("Message from server ", event.data)
    });

    const send = (test: string) => {
        socket.send(test);
    }
    return {send: send};
}