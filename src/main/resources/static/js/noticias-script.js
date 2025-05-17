document.addEventListener("DOMContentLoaded", () => {
    const searchInput = document.getElementById("searchInput");
    const cards = document.querySelectorAll(".news-card");

    searchInput.addEventListener("keyup", () => {
        const query = searchInput.value.toLowerCase().trim();

        cards.forEach(card => {
            const title = card.getAttribute("data-title");
            if (title.includes(query)) {
                card.style.display = "block";
            } else {
                card.style.display = "none";
            }
        });
    });
});
