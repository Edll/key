import {Client} from '@stomp/stompjs';
import {WebSocket} from 'ws';
import {WordMapData} from "./WordMapData";

export const useWebsocket = (onDataReceive: (data) => void) => {

    const client = new Client({
        brokerURL: 'ws://localhost:18080/wordmap',
        onConnect: () => {
            client.subscribe('/topic/word-map', message => {
                    onDataReceive(JSON.parse(message.body) as any);
                    console.log(`Received: data`);
                }
            )
            ;

        },
    });

    client.activate();


    const send = (data: string) => {

        client.publish({destination: '/app/init', body: 'First Message'});
    }

    return {send: send,}
}