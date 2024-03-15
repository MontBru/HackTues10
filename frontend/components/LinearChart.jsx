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
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
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
                    fill: true,
                    backgroundColor: 'rgba(244, 52, 84, 255)',
                    yAxisID: 'y',
                },
            ],
        }
    };

    return <Line options={options} data={data()} />;
}