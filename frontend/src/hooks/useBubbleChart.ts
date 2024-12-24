import {useEffect} from "react";
import {buildSpiralLayout} from "./d3.ts";
import * as d3 from "d3";
import {IBubbleChartData} from "../interfaces/IBubbleChartData.ts";

export const useBubbleChart = (postId: number, data: IBubbleChartData[]) => {

    useEffect(() => {
        const layout = buildSpiralLayout(data);
        const svg = d3.select(`#bubble-chart-${postId}`);
        const g = svg.append('g').attr('transform', 'translate(200,200)');

        // create a tooltip
        const tooltip = d3.select(`#bubble-chart-${postId}-tooltip`)
            .append("div")
            .style("position", "absolute")
            .style("visibility", "hidden")
            .text("I'm a circle!");

        const items = g.selectAll('g.item')
            .data(layout, (d: { id: number; }) => d.id)
            .enter()
            .append('g')
            .classed('item', true)
            .attr('background-color', 'grey')
            .attr('transform', (d: { x: any; y: any; }) => `translate(${d.x},${d.y})`)

             items.append('circle')
            .attr('r', (d: { val: any; }) => d.val)
            .style('fill', 'blue')
            .on("mouseover", function () {
                return tooltip.style("visibility", "visible");
            })
            .on("mousemove", function (event: any) {
                return tooltip.style("top", 1 + "px").style("left", 1 + "px").text(event.val);
            })
            .on("mouseout", function () {
                return tooltip.style("visibility", "hidden");
            });

        items.append('text')
            .text((d: { name: any; }) => d.name)
            .attr('text-anchor', 'middle')
            .attr('alignment-baseline', 'middle')
            .on("mouseover", function () {
                return tooltip.style("visibility", "visible");
            })
            .on("mousemove", function (event: any) {
                return tooltip.style("top", 1 + "px").style("left", 1 + "px");
            })
            .on("mouseout", function () {
                return tooltip.style("visibility", "hidden");
            });



    }, [postId, JSON.stringify(data)]);

}