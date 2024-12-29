import {IWordMapEntry} from "../interfaces/IWordMapData.ts";
import {BubbleChart} from "./BubbleChart.tsx";
import {useMemo, useState} from "react";
import {ISortBy} from "../interfaces/ISortBy.ts";
import {TableHeader} from "./TableHeader.tsx";

interface Props {
    wordMapEntry: IWordMapEntry
}

export const WordMapDetails = ({wordMapEntry}: Props) => {

    const [sortBy, setSortBy] = useState<ISortBy | null>({field: "Count", direction: "desc"})

    const bubbleChartData = useMemo(() => {
        return Object.entries(wordMapEntry.wordMap)
            .filter(([, count]) => count > 2)
            .sort(([, a], [, b]) => b - a)
            .slice(0, 10)
            .reduce((a, [word, count]) => ({...a, [word]: count}), {} as Record<string, number>)
    }, [wordMapEntry.wordMap]);


    const tableData = useMemo(() => {
        return Object.entries(wordMapEntry.wordMap)
            .sort(([wordA, countA], [wordB, countB]) => {
                if (sortBy?.field === "Word") {
                    return sortBy?.direction === "asc" ? wordA.localeCompare(wordB) : wordB.localeCompare(wordA);
                } else if (sortBy?.field === "Count") {
                    return sortBy?.direction === "asc" ? countA - countB : countB - countA;
                } else {
                    return 0;
                }
            })
            .reduce((a, [word, count]) => ({...a, [word]: count}), {} as Record<string, number>);
    }, [wordMapEntry.wordMap, sortBy]);


    return (
        <div>
            <h2>Top Ten</h2>
            <BubbleChart postId={wordMapEntry.postId} data={bubbleChartData}/>
            <h2>All</h2>
            <table>
                <thead>
                <tr>
                    <TableHeader field={"Word"} sortBy={sortBy} onSort={setSortBy}/>
                    <TableHeader field={"Count"} sortBy={sortBy} onSort={setSortBy}/>
                </tr>
                </thead>
                <tbody>
                {Object.entries(tableData).map(([word, count]) => (
                    <tr key={word}>
                        <td>{word}</td>
                        <td>{count as number}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}