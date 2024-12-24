import {IBubbleChartData} from "../interfaces/IBubbleChartData.ts";

export const checkIntersection = (nodes: IBubbleChartData[],
                                  node: IBubbleChartData,
                                  angle: number,
                                  radius: number) => {

    const x = radius * Math.sin(angle) ;
    const y = radius * -Math.cos(angle);

    return nodes.some((n: IBubbleChartData) => n.x && n.y && Math.hypot(n.x - x, n.y - y)
        <= n.val + node.val);
};

export const buildSpiralLayout = (nodes: IBubbleChartData[]) => {
    const ordered = nodes.sort((a, b) => b.val- a.val  );

    let angle = 0;
    let radius = 1;

    return ordered.reduce((all: IBubbleChartData[], node, index) => {
        angle = (index === 0) ? 0 : angle + Math.PI / index;
        while (checkIntersection(all, node, angle, radius)) radius++;
        const x = radius * Math.sin(angle);
        const y = radius * -Math.cos(angle);
        all.push({...node, x, y});
        return all;
    }, []);
}