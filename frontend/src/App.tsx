import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {useWebsocket} from "./useWebsocket";
import {WordMapData} from "./WordMapData";

const App = () => {

    const [wordMapData, setWordDataMap] = useState<WordMapData | null>(null);


    const onDataReceive = (data: WordMapData) => {
        console.log(data);
        setWordDataMap(data);
    }

    let websocket = useWebsocket(onDataReceive);

    console.log("now This", wordMapData);
    return (
        <div className="App">
            <button onClick={() => websocket.send("Rest")}>Click</button>
            {wordMapData !== null && Object.entries(wordMapData as WordMapData).map(([, wordMapData]) =>
                (<div key={wordMapData.postId}>
                        <div>{wordMapData.postId}</div>
                        <ul>
                            {Object.entries(wordMapData.wordMap).map(([word, count]) =>
                                (
                                    <li>{word}: {count}</li>
                                )
                            )

                            }
                        </ul>
                    </div>
                )
            )}
        </div>
    )
        ;
};

export default App;
