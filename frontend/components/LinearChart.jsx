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

export default function LinearChart({title, labels, userData, yAxisText, xAxisText, userData2}) {
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
                    fill: false,
                    backgroundColor: 'rgba(244, 52, 84, 255)',
                },
                // {
                //     label: 'Heart rate',
                //     data: [{x: 0.3, y:userData[0]}, {x: 0.5, y:userData[1]}, {x: 1.3, y:userData[2]}, {x: 1.4, y:userData[3]},
                //         {x: 2.5, y:userData[4]}, {x: 2.6, y:userData[5]}, {x: 3.5, y:userData[6]}, {x: 3.7, y:userData[7]},
                //         {x: 4.3, y:userData[8]}, {x: 4.8, y:userData[9]}, {x: 5.4, y:userData[10]}, {x: 5.5, y:userData[11]},
                //         {x: 6.7, y:userData[12]}, {x: 6.9, y:userData[13]}, {x: 7.3, y:userData[14]}, {x: 7.4, y:userData[15]},],
                //     borderColor: 'rgba(244, 52, 84, 255)',
                //     fill: false,
                //     backgroundColor: 'rgba(244, 52, 84, 255)',
                // }
            ],
        }
    };

    return <Line options={options} data={data()} />;
}