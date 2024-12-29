import {ActivationState, Client} from '@stomp/stompjs';
import {IWordMapData} from "../interfaces/IWordMapData.ts";
import {useCallback, useEffect, useState} from "react";

export const useWebsocket = () => {

    const [data, setData] = useState<IWordMapData | null>(null)

    const [client, setClient] = useState<Client | null>(null)
    const [error, setError] = useState<string | null>(null)

    useEffect(() => {
        const _client = new Client({
            brokerURL: 'ws://localhost:18080/wordmap',
            connectionTimeout: 1000,
            onConnect: () => {
                _client.subscribe('/topic/word-map', message => {
                        setData(JSON.parse(message.body) as IWordMapData);
                        console.log(`Received: data`);
                    }
                );
                _client.subscribe('/topic/update-data', message => {
                        setData(JSON.parse(message.body) as IWordMapData);
                        console.log(`Received: update data`);
                    }
                );
                _client.publish({destination: '/app/init', body: JSON.stringify({init: 1})});
            },
            onWebSocketError: (e,) => {
                console.log(e);
                _client?.deactivate();
                setError("websocket connection error")
                setData(null)
            },
            onStompError: (e) => {
                console.log(e)
                _client?.deactivate();
                setError("stomp error")
                setData(null)
            },
            onDisconnect: () => {
                console.log("socket disconnected")
                _client?.deactivate();
                setError(null)
                setData(null)
            }
        });
        setClient(_client);
    }, []);

    const activateClient = useCallback(() => {
        console.log(`Try activating client`);
        client?.activate();
        setError(null)
    }, [client]);

    const online = client?.state === ActivationState.ACTIVE;

    return {activateClient: activateClient, data: data, online: online, error: error}
}