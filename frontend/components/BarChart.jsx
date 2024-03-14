// import React from 'react';
// import { Bar } from 'react-chartjs-2';
//
// export default function BarChart({title, labels, userData}) {
//     const options = {
//         responsive: true,
//         interaction: {
//             mode: 'index',
//             intersect: false,
//         },
//         stacked: false,
//         plugins: {
//             legend: {
//                 labels: {
//                     color: "white"
//                 }
//             },
//             title: {
//                 display: true,
//                 text: title,
//                 color: "white",
//                 font: {
//                     size: 30
//                 }
//             },
//         },
//         scales: {
//             y: {
//                 ticks: {
//                     color: "white",
//                     stepSize: 10,
//                 },
//                 type: 'linear',
//                 display: true,
//                 position: 'left',
//                 beginAtZero: true
//             },
//             x: {
//                 ticks: {
//                     color: "white",
//                 }
//             }
//         },
//     };
//
//     const data = () => {
//         return {
//             labels,
//             datasets: [
//                 {
//                     label: 'Heart rate',
//                     data: userData,
//                     borderColor: 'rgba(244, 52, 84, 255)',
//                     fill: true,
//                     backgroundColor: 'rgba(244, 52, 84, 255)',
//                     yAxisID: 'y',
//                 },
//             ],
//         }
//     };
//
//     return <Bar options={options} data={data()} />;
// }

import {Bar} from "react-chartjs-2";
import {
    Chart as ChartJS,
    BarElement,
    CategoryScale,
    Tooltip,
    Legend, LinearScale
} from 'chart.js';

ChartJS.register(
    BarElement,
    CategoryScale,
    LinearScale,
    Tooltip,
    Legend
);

const BarChart = ({title, labels, userData}) => {
    const data = {
        labels: labels,
        datasets: [
            {
                label: 'Student interest',
                data: userData,
                backgroundColor: 'rgba(244, 52, 84, 150)',
            }
        ]
    }

    const options = {
        responsive: true,
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
                    stepSize: 1,
                },
                display: true,
                position: 'left',
                beginAtZero: true
            },
            x: {
                ticks: {
                    color: "white",
                }
            }
        },
    };

    return (
        <Bar data={data} options={options}></Bar>
    )
}

export default BarChart;