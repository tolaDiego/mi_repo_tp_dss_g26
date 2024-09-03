

//TODO: agregar ofertas nuevas
document.getElementById("newOfferForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const title = document.getElementById("offerTitle").value;
    const description = document.getElementById("offerDescription").value;
    const points = document.getElementById("offerPoints").value;
    const imageUrl = document.getElementById("offerImage").value;
    const category = document.getElementById("offerCategory").value; // Obtiene la categoría seleccionada

    const newCard = `
        <div class="card">
            <img src="${imageUrl}" alt="${title}">
            <div class="card-content">
                <h4>${title}</h4>
                <p>${description}</p>
                <p><strong>Categoría: ${category}</strong></p> <!-- Muestra la categoría en la card -->
                <p><strong>Coste: ${points} Puntos</strong></p>
                <button class="redeem-button">Canjear</button>
            </div>
        </div>
    `;

    document.getElementById("cardsContainer").innerHTML += newCard;

    // Limpia el formulario después de añadir la oferta
    document.getElementById("newOfferForm").reset();

    // Reasignar los eventos de canje para las nuevas cards
    assignRedeemEvents();
});


function assignRedeemEvents() {
    document.querySelectorAll('.redeem-button').forEach(button => {
        button.addEventListener('click', function() {
            const cost = parseInt(this.previousElementSibling.textContent.match(/\d+/)[0]);
            const userPointsElement = document.getElementById('userPoints');
            let userPoints = parseInt(userPointsElement.textContent);

            if (userPoints >= cost) {
                userPoints -= cost;
                userPointsElement.textContent = userPoints;
                alert('Oferta canjeada con éxito!');
            } else {
                alert('No tienes suficientes puntos para canjear esta oferta.');
            }
        });
    });
}

// Llama a la función para asignar eventos de canje a las cards iniciales
assignRedeemEvents();


//TODO descuenta puntos segun la oferta canjeada
document.querySelectorAll('.redeem-button').forEach(button => {
    button.addEventListener('click', function() {
        const cost = parseInt(this.previousElementSibling.textContent.match(/\d+/)[0]);
        const userPointsElement = document.getElementById('userPoints');
        let userPoints = parseInt(userPointsElement.textContent);

        if (userPoints >= cost) {
            userPoints -= cost;
            userPointsElement.textContent = userPoints;
            alert('Oferta canjeada con éxito!');
        } else {
            alert('No tienes suficientes puntos para canjear esta oferta.');
        }
    });
});