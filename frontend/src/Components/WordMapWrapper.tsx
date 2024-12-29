import {IWordMapData, IWordMapEntry} from "../interfaces/IWordMapData.ts";
import {WordMap} from "./WordMap.tsx";
import {useMemo} from "react";

interface Props {
    data: IWordMapData | null;
}

export const WordMapWrapper = ({data}: Props) => {

    const wordMapData = useMemo(() => {
        return data;
    }, [data]);

    return (
        <div>
            {wordMapData === null && <h1>Loading...</h1>}
            {wordMapData !== null && Object.entries(wordMapData as IWordMapData).map(([, wordMapData]) =>
                (<WordMap wordMapEntry={wordMapData as IWordMapEntry} key={`word-map-${wordMapData.postId}`}/>
                )
            )}
        </div>
    )
}