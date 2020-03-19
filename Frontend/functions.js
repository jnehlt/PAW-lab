var testingFunctionCalls = 0;

function testingFunction() {
    testingFunctionCalls++;
    if (testingFunctionCalls > 3) {
        alert("Dobra, chyba ci się nudzi")
    } else
        alert("Super! A teraz kliknij drugiego!");
}
