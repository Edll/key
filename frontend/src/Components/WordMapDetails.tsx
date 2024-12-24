import {IWordMapEntry} from "../interfaces/IWordMapData.ts";
import {BubbleChart} from "./BubbleChart.tsx";

interface Props {
    wordMapEntry: IWordMapEntry
}

export const WordMapDetails = ({wordMapEntry}: Props) => {

    return (
        <div>
            words: {wordMapEntry.wordMap.length}
            <BubbleChart postId={wordMapEntry.postId} data={wordMapEntry.wordMap}/>
        </div>
    )
}