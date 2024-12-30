import {ActivationState, Client} from '@stomp/stompjs';
import {IWordMapData} from "../interfaces/IWordMapData.ts";
import {useCallback, useEffect, useMemo, useState} from "react";
import {WS_ENDPOINT} from "../statics.ts";



export const useWebsocket = () => {

    const [data, setData] = useState<IWordMapData | null>(null)
    const [client, setClient] = useState<Client | null>(null)
    const [error, setError] = useState<string | null>(null)

    const [connectOnStart, setConnectOnStart] = useState(false)

    useEffect(() => {
        const _client = new Client({
            brokerURL: WS_ENDPOINT,
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

    const isActive = useMemo(() => {
        let state = client?.state === ActivationState.ACTIVE;
        console.log(state ? "Websocket is active" : "Websocket is inactive");
        return state;
    }, [client?.state]);

    const activateClient = useCallback(() => {
        console.log(`Try activating client`, client !== undefined);
        client?.activate();
        setError(null)
    }, [client]);

    useEffect(() => {
        if(!connectOnStart && !isActive && client){
            console.log("Starting client", client)
            activateClient();
            setConnectOnStart(true);
        }
    }, [connectOnStart, setConnectOnStart, isActive, activateClient, client]);


    return {activateClient: activateClient, data: data, online: isActive, error: error}
}