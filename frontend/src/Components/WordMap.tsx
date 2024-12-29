import {IWordMapEntry} from "../interfaces/IWordMapData.ts";
import {useState} from "react";
import {WordMapDetails} from "./WordMapDetails.tsx";

interface Props {
    wordMapEntry: IWordMapEntry
}

export const WordMap = ({wordMapEntry}: Props) => {
    const [expand, setExpand] = useState(false);

    return (
        <div className={'key-word-map'}>
            <div
                className={'key-word-map__header'}
                onClick={() => setExpand(!expand)}>
                {wordMapEntry.postTitle}
            </div>
            {expand &&
                <WordMapDetails wordMapEntry={wordMapEntry} key={`post-details-${wordMapEntry.postId}`}/>
            }
        </div>


    );
}