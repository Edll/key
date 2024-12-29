import React from 'react';
import {useWebsocket} from "../hooks/useWebsocket";
import {WordMapWrapper} from "./WordMapWrapper.tsx";

const App = () => {


    const websocket = useWebsocket();

    return (
        <div className="key-app-root">

            {!websocket.online ?
                <div>
                    <h1>Server Offline</h1>
                    <button onClick={() => websocket.activateClient()}>Try Reconnect</button>
                    {websocket.error && <p className="key-error">
                        {websocket.error}
                    </p>
                    }
                </div>
                :
                <WordMapWrapper data={websocket.data}/>
            }
        </div>
    )
        ;
};

export default App;
