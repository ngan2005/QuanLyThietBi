<template>
  <nav class="navbar glass">
    <div class="nav-brand">
      <span class="brand-text">Quản lý Thiết bị</span>
    </div>
    <div class="nav-links">
      <router-link to="/" class="nav-link">Dashboard</router-link>
      <router-link to="/employees" class="nav-link">Nhân viên</router-link>
      <router-link to="/devices" class="nav-link">Thiết bị</router-link>
    </div>
  </nav>
</template>

<style scoped>
.navbar {
  display: flex;
  align-items: center;
  padding: 1rem 2rem;
  margin: 1rem;
  position: sticky;
  top: 1rem;
  z-index: 100;
  border-bottom: 1px solid var(--border-color);
  background: var(--bg-main);
}

.nav-brand {
  display: flex;
  align-items: center;
  gap: 0.75rem;
}
.brand-text {
  font-family: 'Outfit', sans-serif;
  font-size: 1.25rem;
  font-weight: 600;
  color: var(--text-main);
}

.nav-links {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 0.5rem;
}

.nav-link {
  color: var(--text-muted);
  text-decoration: none;
  padding: 0.5rem 1rem;
  border-radius: 8px;
  transition: all 0.2s ease;
}

.nav-link:hover {
  color: var(--text-main);
  background: rgba(0, 0, 0, 0.05);
}

.nav-link.router-link-active {
  color: var(--primary);
  background: rgba(13, 110, 253, 0.1);
}
</style>
