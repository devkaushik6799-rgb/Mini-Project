import type { Medicine } from './types';
const API = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';
export async function getMedicines(search=''): Promise<Medicine[]> {
  const response = await fetch(`${API}/medicines${search ? `?search=${encodeURIComponent(search)}` : ''}`);
  if (!response.ok) throw new Error('Could not load medicines');
  return response.json();
}
export async function placeOrder(items:{medicineId:number; quantity:number}[]) {
  const response = await fetch(`${API}/orders`, {method:'POST', headers:{'Content-Type':'application/json'}, body:JSON.stringify({customerName:'Demo Customer', customerEmail:'demo@example.com', items})});
  if (!response.ok) throw new Error('Order could not be placed');
  return response.json();
}
export async function askAssistant(message:string):Promise<string> {
  const response = await fetch(`${API}/chat`, {method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({message})});
  if (!response.ok) throw new Error('Assistant is temporarily unavailable');
  const data = await response.json(); return data.reply;
}
