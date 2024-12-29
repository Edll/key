import {IBubbleChartData} from "../interfaces/IBubbleChartData.ts";


export const buildLayout = (nodes: IBubbleChartData[], marginItem: number) => {

    const all = [] as IBubbleChartData[];

    let lastX = 0;

    nodes.forEach((node, index) => {
        const nextVal = nodes[index + 1] === undefined ? 0 : nodes[index + 1].val;

        all.push({...node, x: lastX, y: 0})

        const dist = node.val + nextVal + marginItem
        lastX = dist + lastX;
    })

    return all;
}