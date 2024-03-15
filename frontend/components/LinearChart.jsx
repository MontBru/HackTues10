import React, {useState} from 'react';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    Filler
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    Filler
);

export default function LinearChart({title, labels, userData, yAxisText, xAxisText}) {
    const options = {
        responsive: true,
        interaction: {
            mode: 'index',
            intersect: false,
        },
        stacked: false,
        plugins: {
            legend: {
                labels: {
                    color: "white"
                },
                position: "top",
                align: "end"
            },
            title: {
                display: true,
                text: title,
                align: "start",
                color: "white",
                font: {
                    size: 30
                }
            },
        },
        scales: {
            y: {
                ticks: {
                    color: "white",
                    stepSize: 10,
                },
                type: 'linear',
                display: true,
                position: 'left',
                beginAtZero: true,
                title: {
                    text: yAxisText,
                    display: true,
                    color: "white",
                    padding: 10,
                    font: {
                        size: 16
                    }
                }
            },
            x: {
                ticks: {
                    color: "white",
                },
                title: {
                    text: xAxisText,
                    display: true,
                    color: "white",
                    padding: 10,
                    font: {
                        size: 16
                    }
                }
            }
        },
    };

    const data = () => {
        return {
            labels,
            datasets: [
                {
                    label: 'Heart rate',
                    data: userData,
                    borderColor: 'rgba(244, 52, 84, 255)',
                    borderWidth: 2,
                    pointRadius: 0,
                    fill: true,
                    backgroundColor:(context)=>{
                        const bgColor = ['rgba(244, 52, 84, 255)', 'rgba(0,0,0,0)'];
                        console.log(context.chart.chartArea)
                        if(!context.chart.chartArea)
                            return;
                        const {ctx, data, chartArea:{top, bottom}} = context.chart;
                        const gradientBg = ctx.createLinearGradient(0,top,0,bottom);
                        gradientBg.addColorStop(0, bgColor[0]);
                        gradientBg.addColorStop(1, bgColor[1]);
                        return gradientBg;
                    },
                    yAxisID: 'y',
                },
            ],
        }
    };

    return <Line options={options} data={data()} />;
}