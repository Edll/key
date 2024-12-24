import {IWordMapEntry} from "../interfaces/IWordMapData.ts";

interface Props {
    wordMapEntry: IWordMapEntry
}

export const WordMapDetails = ({wordMapEntry}: Props) => {

    return (
        <div>
            words: {wordMapEntry.wordMap.length}
        </div>
    )
}