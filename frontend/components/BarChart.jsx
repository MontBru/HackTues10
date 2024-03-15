import {Bar} from "react-chartjs-2";
import deferredPlugin from 'chartjs-plugin-deferred';

import {
    Chart as ChartJS,
    BarElement,
    CategoryScale,
    Tooltip,
    Legend, LinearScale
} from 'chart.js';

ChartJS.register(
    deferredPlugin,
    BarElement,
    CategoryScale,
    LinearScale,
    Tooltip,
    Legend
);

const BarChart = ({title, labels, userData, xAxisText, yAxisText, datasetLabel}) => {
    const data = {
        labels: labels,
        datasets: [
            {
                label: datasetLabel,
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
                align: "start",
                color: "white",
                font: {
                    size: 30
                }
            },
            deferred: {
                enabled: true,
                yOffset: '70%', // defer until 70% of the canvas height are inside the viewport
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

    return (
        <Bar data={data} options={options}></Bar>
    )
}

export default BarChart;