import React, {useState} from 'react';
import {useWebsocket} from "../hooks/useWebsocket";
import {IWordMapData, IWordMapEntry} from "../interfaces/IWordMapData";
import {WordMap} from "./WordMap.tsx";

const App = () => {

    const [wordMapData, setWordDataMap] = useState<IWordMapData | null>(null);


    const onDataReceive = (data: IWordMapData) => {
        console.log(data);
        setWordDataMap(data);
    }

    let websocket = useWebsocket(onDataReceive);

    console.log("now This", wordMapData);
    return (
        <div className="App">
            <button onClick={() => websocket.send("Rest")}>Click</button>
            {wordMapData !== null && Object.entries(wordMapData as IWordMapData).map(([, wordMapData]) =>
                (<WordMap wordMapEntry={wordMapData as IWordMapEntry}/>
                )
            )}
        </div>
    )
        ;
};

export default App;
