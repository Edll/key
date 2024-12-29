import {useEffect} from "react";
import {buildLayout} from "../helper/d3Layout.ts";
import * as d3 from "d3";
import {IBubbleChartData} from "../interfaces/IBubbleChartData.ts";

export const useBubbleChart = (postId: number, data: IBubbleChartData[]) => {

    useEffect(() => {
        const margin = 8;
        const marginItem = 8;
        const layout = buildLayout(data, marginItem);
        const svg = d3.select(`#bubble-chart-${postId}`);
        const g = svg.append('g');

        const width = data.reduce((partialSum, a) => partialSum + a.val + marginItem, 0);
        const height = Math.max(...data.map(a => a.val));

        svg.attr('width', width * 2 + margin)
        svg.attr('height', height * 2 + margin)
        g.attr('transform', `translate(${height + margin},${height + margin})`);

        const color = d3.schemeCategory10;

        const items = g.selectAll('g.item')
            .data(layout, (d: { id: number; }) => d.id)
            .enter()
            .append('g')
            .classed('item', true)
            .attr('background-color', 'grey')
            .attr('transform', (d: { x: any; y: any; }) => `translate(${d.x},${d.y})`)

        items.append('circle')
            .attr('r', (d: { val: any; }) => d.val)
            .style("fill", (v, index) => color[index])

        const innerG = items.append('g');

        innerG.append('text')
            .text((d) => d.val)
            .attr('text-anchor', 'middle')
            .attr('alignment-baseline', 'middle')


        innerG.append('text')
            .text((d) => d.name)
            .attr('text-anchor', 'middle')
            .attr('alignment-baseline', 'middle')
            .attr('transform', 'translate(0, 16)')
            .attr('font-size', '0.5rem')


    }, [postId, JSON.stringify(data)]);

}