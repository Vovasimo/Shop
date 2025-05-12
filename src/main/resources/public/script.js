const API_BASE = "";

async function fetchProducts() {
    const res = await fetch(`${API_BASE}/products`);
    const data = await res.json();
    return data.data;
}

async function fetchCartIds() {
    const res = await fetch(`${API_BASE}/cart`);
    const data = await res.json();
    return data.data.map(item => item.id);
}

async function updateCartCount() {
    try {
        const res = await fetch("/cart");
        const data = await res.json();
        const count = data.data.length;
        const badge = document.getElementById("cartCount");

        if (badge) {
            if (count > 0) {
                badge.textContent = count;
                badge.style.display = "inline-block";
            } else {
                badge.style.display = "none";
            }
        }
    } catch (e) {
        console.error("Cart count error:", e);
    }
}

async function renderProductCard(item, cartIds) {
    const col = document.createElement("div");
    col.className = "col-md-3";

    const card = document.createElement("div");
    card.className = "card h-100 shadow-sm animate__animated animate__fadeInUp";
    card.onclick = () => window.location.href = `product.html?id=${item.id}`;

    const inCart = cartIds.includes(item.id);

    card.innerHTML = `
        <div class="card-body d-flex flex-column justify-content-between">
            <h5 class="card-title">${item.name}</h5>
            <p class="card-text">${item.description}</p>
            <div class="mt-auto d-flex justify-content-between align-items-center">
                <strong>${item.price.toFixed(2)} $</strong>
                <button class="btn btn-sm ${inCart ? "btn-warning" : "btn-add"}" ${inCart ? "disabled" : ""}>
                    ${inCart ? "✔ In Cart" : "+ Add"}
                </button>
            </div>
        </div>
    `;

    const button = card.querySelector("button");
    if (!inCart) {
        button.onclick = async (e) => {
            e.stopPropagation();
            await fetch(`${API_BASE}/cart/add`, {
                method: "POST",
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(item)
            });

            button.className = "btn btn-sm btn-warning";
            button.textContent = "✔ In Cart";
            button.disabled = true;

            updateCartCount();
        };
    }

    col.appendChild(card);
    document.getElementById("products").appendChild(col);
}

window.onload = async () => {
    if (document.getElementById("products")) {
        const [products, cartIds] = await Promise.all([
            fetchProducts(),
            fetchCartIds()
        ]);

        for (const product of products) {
            await renderProductCard(product, cartIds);
        }

        updateCartCount();
    }
};
