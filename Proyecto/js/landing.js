document.addEventListener('DOMContentLoaded', function () {
    // Funcionalidad FAQ
    var questions = document.querySelectorAll('.faq-question');
    questions.forEach(function (question) {
        question.addEventListener('click', function () {
            var answer = this.nextElementSibling;
            answer.style.display = answer.style.display === 'block' ? 'none' : 'block';
        });
    });

    // Funcionalidad del formulario de contacto
    document.getElementById('contactForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Evita el envío del formulario

        // Obtiene los valores del formulario
        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const message = document.getElementById('message').value;

        // Valida los campos
        if (!name || !email || !message) {
            displayResponseMessage('Por favor, complete todos los campos.');
            return;
        }

        // Muestra un mensaje de éxito
        displayResponseMessage('¡Mensaje enviado con éxito!');

        // Limpia el formulario
        document.getElementById('contactForm').reset();
    });

    function displayResponseMessage(message) {
        const responseMessageElement = document.getElementById('responseMessage');
        responseMessageElement.textContent = message;
    }

    // Funcionalidad del acordeón
    var acc = document.querySelectorAll('.accordion-button');

    acc.forEach(function(button) {
        button.addEventListener('click', function() {
            // Alternar la clase 'active' para el botón
            this.classList.toggle('active');

            // Seleccionar el panel relacionado
            var panel = this.nextElementSibling;

            // Alternar la altura máxima del panel
            if (panel.style.display === 'block') {
                panel.style.display = 'none';
            } else {
                // Ocultar todos los paneles
                document.querySelectorAll('.accordion-panel').forEach(function(p) {
                    p.style.display = 'none';
                });
                // Mostrar el panel actual
                panel.style.display = 'block';
            }
        });
    });
});
