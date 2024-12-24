import {IWordMapEntry} from "../interfaces/IWordMapData.ts";
import {useState} from "react";
import {WordMapDetails} from "./WordMapDetails.tsx";

interface Props {
    wordMapEntry: IWordMapEntry
}

export const WordMap = ({wordMapEntry}: Props) => {
    const [expand, setExpand] = useState(false);


    return (
        <div>
            <h1>
                <button onClick={() => setExpand(!expand)}>
                    {wordMapEntry.postId} {wordMapEntry.postTitle}
                </button>
            </h1>
            {expand && <WordMapDetails wordMapEntry={wordMapEntry}/>
            }
        </div>


    );
}