# Team Managing Service

## Опис
Team Managing Service — це простий CRUD-сервіс для управління командами, створений у тестових цілях для налагодження деплойменту на кластер Kubernetes.

Проєкт містить конфігураційні файли для Kubernetes у папці `k8s`.

## Передумови
Перед розгортанням проєкту необхідно виконати наступні дії:

1. Створити `namespace`:

```bash
kubectl create namespace ktzv
```

2. Визначити `secrets` для бази даних із назвою `db-secrets` та ключами:
    - `DB_USERNAME`
    - `DB_PASSWORD`
    - `DB_NAME`

   Приклад створення секретів:

   ```bash
   kubectl create secret generic db-secrets \
     --namespace=ktzv \
     --from-literal=DB_USERNAME=<ваш_користувач> \
     --from-literal=DB_PASSWORD=<ваш_пароль> \
     --from-literal=DB_NAME=<назва_бази>
   ```

## Розгортання
Розгортання сервісу виконується за допомогою конфігураційних файлів, які розташовані у папці `k8s`. Застосовуйте файли у наступній черзі:

1. Розгортання бази даних:

   ```bash
   kubectl apply -f k8s/db.yaml --namespace=ktzv
   ```

2. Розгортання сервісу Team Managing:

   ```bash
   kubectl apply -f k8s/team-managing.yaml --namespace=ktzv
   ```

## Перевірка стану
Після розгортання переконайтеся, що всі ресурси створені та працюють коректно:

```bash
kubectl get all --namespace=ktzv
```

## Видалення
Для видалення сервісу та пов'язаних ресурсів використовуйте наступні команди:

1. Видалення бази даних:

   ```bash
   kubectl delete -f k8s/db.yaml --namespace=ktzv
   ```

2. Видалення сервісу Team Managing:

   ```bash
   kubectl delete -f k8s/team-managing.yaml --namespace=ktzv
   ```

3. Видалення секретів:

   ```bash
   kubectl delete secret db-secrets --namespace=ktzv
   ```

4. Видалення `namespace`:

   ```bash
   kubectl delete namespace ktzv
   ```

## Ліцензія
Цей проєкт створений виключно для навчальних цілей.
