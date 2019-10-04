const express = require('express'),
    http = require('http'),
    app = express(),
    server = http.createServer(app),
    io = require('socket.io').listen(server);

app.get('/', (req, res) => {
    res.send('Chat Server is running on port 3000')
});
io.on('connection', (socket) => {
    console.log('user connected');

    //유저 조인
    socket.on('join', function(userNickname) {
        console.log(userNickname +" : has joined the chat "  );
        socket.broadcast.emit('userjoinedthechat',userNickname +" : has joined the chat ");
    });

    //메시지 보냄
    socket.on('messagedetection', (senderNickname,messageContent) => {
        //log the message in console
        console.log(senderNickname+" : " +messageContent);
        //create a message object
        let  message = {"message":messageContent, "senderNickname":senderNickname};
        // send the message to all users including the sender  using io.emit()
        io.emit('message', message )
    });

    //유저 나감
    socket.on('disconnect', function(userNickname) {
        console.log(userNickname +' has left ');
        socket.broadcast.emit( "userdisconnect" ,' user has left')
    })

});

server.listen(3000, process.env.local ,()=>{
    console.log('Node app is running on port 3000')
});