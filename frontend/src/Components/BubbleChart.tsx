import {useBubbleChart} from "../hooks/useBubbleChart.ts";
import {useMemo} from "react";
import {IBubbleChartData} from "../interfaces/IBubbleChartData.ts";

interface Props {
    postId: number
    data: { [word: string]: number }
}

export const BubbleChart = ({postId, data}: Props) => {


    const convertedData = useMemo(() => {
        return Object.entries(data).map(([word, count], index) => {
            return {
                name: word,
                val: count,
                id: index,
            } as IBubbleChartData
        })
    }, [data]);

    useBubbleChart(postId, convertedData);

    return (<div style={{position: 'relative'}}>
        <svg className={'key-bubble-chart'} id={`bubble-chart-${postId}`}/>

    </div>)
}