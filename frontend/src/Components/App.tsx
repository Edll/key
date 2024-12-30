import {useWebsocket} from "../hooks/useWebsocket";
import {WordMapWrapper} from "./WordMapWrapper.tsx";
import {WebsocketState} from "./WebsocketState.tsx";

const App = () => {


    const websocket = useWebsocket();

    return (
        <div className="key-app-root">

            {!websocket.online ?
                <WebsocketState error={websocket.error} activateClient={websocket.activateClient}/>
                : <WordMapWrapper data={websocket.data}/>
            }
        </div>
    )
        ;
};

export default App;
