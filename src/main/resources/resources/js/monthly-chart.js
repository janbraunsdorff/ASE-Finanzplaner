window.addEventListener('load', () => {
    var ctx2 = document.getElementById("courseChart");
    var dashboardChart2 = new Chart(ctx2, {
        type: 'line',
        data: {
            labels: ['xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx'],
            datasets: [{
                data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 6, 7, 5, 5, 3, 5, 6, 7, 9, 2, 4, 6, 10, 1, 4, 5, 8, 3, 7],
                lineTension: 0,
                backgroundColor: [
                    'rgba(2, 136, 209, 0.2)',
                ],
                borderColor: [
                    'rgba(2, 136, 209, 1)',
                ]
            }]

        },
        options: {
            legend: false,
            scales: {
                xAxes: [{
                    ticks: {
                        autoSkip: true,
                        maxTicksLimit: 17,
                        maxRotation: 0,
                        minRotation: 0,
                        fontColor: 'rgba(200,200,200,1)',
                    }
                }]
            }
        }
    });

    var ctx2 = document.getElementById("totalChart");
    var dashboardChart2 = new Chart(ctx2, {
        type: 'line',
        data: {
            labels: ['xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx', 'xx.xx'],
            datasets: [{
                data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 8, 7, 6, 5, 6, 7, 5, 5, 3, 5, 6, 7, 9, 2, 4, 6, 10, 1, 4, 5, 8, 3, 7],
                lineTension: 0,
                backgroundColor: [
                    'rgba(2, 136, 209, 0.2)',
                ],
                borderColor: [
                    'rgba(2, 136, 209, 1)',
                ]
            }]

        },
        options: {
            legend: false,
            scales: {
                xAxes: [{
                    ticks: {
                        autoSkip: true,
                        maxTicksLimit: 17,
                        maxRotation: 0,
                        minRotation: 0,
                        fontColor: 'rgba(200,200,200,1)',
                    }
                }]
            }
        }
    });

    var ctx2 = document.getElementById("outputCategorie");
    var dashboardChart2 = new Chart(ctx2, {
        type: 'pie',
        data: {
            labels: ['Kategorie 1', 'Person 2', 'Person 3', 'Person 4', 'Person 5', 'Person 6'],
            datasets: [{
                data: [2, 2, 2, 2, 2, 2],
                lineTension: 0,
                backgroundColor: [
                    'rgba(239, 83, 80, 0.5)',
                    'rgba(244, 67, 54, 0.5)',
                    'rgba(229, 57, 53, 0.5)',
                    'rgba(211, 47, 47, 0.5)',
                    'rgba(198, 40, 40, 0.5)',
                    'rgba(183, 28, 28, 0.5)',
                ],
                borderColor: [
                    '#ef5350',
                    '#f44336',
                    '#e53935',
                    '#d32f2f',
                    '#c62828',
                    '#b71c1c'
                ]
            }]

        },
        options: {
            legend: {
                position: 'right',
            },
            responsive: true,
            maintainAspectRatio: true,
        }
    });
    var ctx2 = document.getElementById("outputPerson");
    var dashboardChart2 = new Chart(ctx2, {
        type: 'pie',
        data: {
            labels: ['Person 1', 'Person 2', 'Person 3', 'Person 4', 'Person 5', 'Person 6'],
            datasets: [{
                data: [2, 2, 2, 2, 2, 2],
                lineTension: 0,
                backgroundColor: [
                    'rgba(239, 83, 80, 0.5)',
                    'rgba(244, 67, 54, 0.5)',
                    'rgba(229, 57, 53, 0.5)',
                    'rgba(211, 47, 47, 0.5)',
                    'rgba(198, 40, 40, 0.5)',
                    'rgba(183, 28, 28, 0.5)',
                ],
                borderColor: [
                    '#ef5350',
                    '#f44336',
                    '#e53935',
                    '#d32f2f',
                    '#c62828',
                    '#b71c1c'
                ]
            }]

        },
        options: {
            legend: {
                position: 'right',

            }
        }
    });

    var ctx2 = document.getElementById("inPerson");
    var dashboardChart2 = new Chart(ctx2, {
        type: 'pie',
        data: {
            labels: ['Person 1', 'Person 2', 'Person 3', 'Person 4', 'Person 5', 'Person 6'],
            datasets: [{
                data: [2, 2, 2, 2, 2, 2],
                lineTension: 0,
                backgroundColor: [
                    'rgba(156, 204, 101, 0.5)',
                    'rgba(139, 195, 74, 0.5)',
                    'rgba(124, 179, 66, 0.5)',
                    'rgba(104, 159, 56, 0.5)',
                    'rgba(85, 139, 47, 0.5)',
                    'rgba(51, 105, 30, 0.5)',
                ],
                borderColor: [
                    '#9ccc65',
                    '#8bc34a',
                    '#7cb342',
                    '#689f38',
                    '#558b2f',
                    '#33691e',
                ]
            }]

        },
        options: {
            legend: {
                position: 'right',

            }
        }
    });

    var ctx2 = document.getElementById("inCategorie");
    var dashboardChart2 = new Chart(ctx2, {
        type: 'pie',
        data: {
            labels: ['Person 1', 'Person 2', 'Person 3', 'Person 4', 'Person 5', 'Person 9'],
            datasets: [{
                data: [2, 2, 2, 2, 2, 2],
                lineTension: 0,
                backgroundColor: [
                    'rgba(156, 204, 101, 0.5)',
                    'rgba(139, 195, 74, 0.5)',
                    'rgba(124, 179, 66, 0.5)',
                    'rgba(104, 159, 56, 0.5)',
                    'rgba(85, 139, 47, 0.5)',
                    'rgba(51, 105, 30, 0.5)',
                ],
                borderColor: [
                    '#9ccc65',
                    '#8bc34a',
                    '#7cb342',
                    '#689f38',
                    '#558b2f',
                    '#33691e',
                ]
            }]

        },
        options: {
            legend: {
                position: 'right',
            },
        }
    });
});

