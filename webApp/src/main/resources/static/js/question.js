//scripts for the AJAX requests
document.addEventListener("DOMContentLoaded", function () {
    //50-50 button
    function useLifeline_0() {
        var button = document.getElementById("button-0");

        fetch('/api/L_fifty_fifty')
            .then(response => response.text())
            .then(text => JSON.parse(text)) //convert the response into json
            .then(data => {
                button.disabled = true; //disable the 50-50 button
                document.getElementById("lifelineUsed").innerText = button.innerText;

                document.getElementById("answer-"+data[0]).disabled = true; //disable the two wrong options
                document.getElementById("answer-"+data[1]).disabled = true;   
            })
            .catch(error => console.error('Errore:', error));
    }
    
    //telephone button
    function useLifeline_1(){
        var button = document.getElementById("button-1");

        fetch('/api/L_telephone')
            .then(response => response.text())
            .then(data => {
                button.disabled = true;
                document.getElementById("lifelineUsed").innerText = button.innerText;

                //---- timer logic
                let timeLeft = 60;
                let timerDisplay = document.getElementById("lifelineResult");

                timerDisplay.innerText = "Time left: " + timeLeft + "s";

                // Function called every second to update the counter
                let countdown = setInterval(function () {
                    timeLeft--;
                    timerDisplay.innerText = "Time left: " + timeLeft + "s";

                    if (timeLeft <= 0) {
                        clearInterval(countdown); // Stop the timer
                        timerDisplay.innerText = "Time is up!";
                    }
                }, 1000);
            })
            .catch(error => console.error('Errore:', error));
    }

    //double dip button 
    function useLifeline_2(){
        var button = document.getElementById("button-2");

        fetch('/api/L_double_dip')
            .then(response => response.text())
            .then(data => {
                button.disabled = true;
                document.getElementById("lifelineUsed").innerText = button.innerText;
            })
            .catch(error => console.error('Errore:', error));
    }

    //ask audience button
    function useLifeline_3(){
        var button = document.getElementById("button-3");

        fetch('/api/L_ask_audience')
            .then(response => response.text())
            .then(text => JSON.parse(text))
            .then(data => {
                button.disabled = true;
                document.getElementById("lifelineUsed").innerText = button.innerText;

                let result = "";
                for(let i = 0; i < 4; i++){
                    result += String.fromCharCode(65+i) + ": " + (Math.round(data[i].toFixed(2)*100)) + "%      ";
                }
                document.getElementById("lifelineResult").innerText = result;
            })
            .catch(error => console.error('Errore:', error));
    }

    //this function is called every time an answer button is clicked
    function processAnswer(i){
        var button = document.getElementById("answer-" + i);
        button.disabled = true;
        button.classList.add("hold-button");

        var timeOut_answer = 2000;
        var timeOut_checking = 5000;

        // Save the state of all buttons and disable them to prevent multiple clicks
        const states = [];
        for (let j = 0; j < 4; j++) {
            const currButton = document.getElementById("answer-" + j);
            if (currButton) { 
                states[j] = currButton.disabled;
                currButton.disabled = true;
            } else {
                console.warn(`Button with ID "answer-${j}" not found`);
            }
        }

        setTimeout(() => {
            fetch('/api/isCorrect?answerId='+i) //check if the answer is correct or not
            .then(response => response.text())
            .then(text => JSON.parse(text))
            .then(data => {
                console.log(data);

                //check if answer is correct or not
                if(data){
                    button.classList.add("correct-button"); 
                    button.classList.remove("hold-button"); 

                    setTimeout(() => {
                        window.location.href = "/nextQuestion";
                    }, timeOut_answer);
                }
                else{
                    fetch('/api/hasAdditionalLife')
                        .then(response => response.text())
                        .then(text => JSON.parse(text))
                        .then(data => {
                            console.log(data);
                                
                            button.classList.add("wrong-button");
                            button.classList.remove("hold-button");

                            //if no additionalLife -> GAMEOVER
                            if(!data){
                                setTimeout(() => {
                                    window.location.href = "/gameOver";
                                }, timeOut_answer);
                            }
                            else{
                                fetch('/api/removeAdditionalLife');
                                
                                //restore the states of the buttons
                                for(let j = 0; j < 4; j++){
                                    if(j == i){
                                        continue;
                                    }
                                    var currButton = document.getElementById("answer-"+j);
                                    currButton.disabled = states[j];
                                }
                            }
                        })
                        .catch(error => console.error('Errore:', error));
                }
            })
            .catch(error => console.error('Errore:', error));
        }, timeOut_checking);
    }

    // Export the functions
    window.useLifeline_0 = useLifeline_0;
    window.useLifeline_1 = useLifeline_1;
    window.useLifeline_2 = useLifeline_2;
    window.useLifeline_3 = useLifeline_3;
    window.processAnswer = processAnswer;
});
