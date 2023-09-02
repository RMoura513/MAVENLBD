CREATE DATABASE cpfbd
GO
USE cpfbd
GO
CREATE TABLE cliente (
CPF						CHAR(11)		NOT NULL,
nome					VARCHAR(100)	NOT NULL,
email					VARCHAR(200)	NOT NULL,
limite_de_credito		DECIMAL(7,2)	NOT NULL,
dt_nascimento			DATE			NOT NULL
PRIMARY KEY (CPF)
)

CREATE PROCEDURE sp_crud_cliente
(
	@acao CHAR(1), -- 'I' para insert, 'U' para update, 'D' para delete
    @CPF CHAR(11),
    @nome VARCHAR(100),
    @email VARCHAR(200),
    @limite_de_credito DECIMAL(7,2),
    @dt_nascimento DATE,
	@saida VARCHAR(100) OUTPUT
    
)
AS
BEGIN
    IF @acao = 'I'
    BEGIN
        -- Verificar se o CPF � v�lido e n�o cont�m 11 n�meros repetidos
        IF (LEN(@CPF) = 11) AND (NOT EXISTS (SELECT 1 FROM cliente WHERE CPF = @CPF))
        BEGIN
            INSERT INTO cliente (CPF, nome, email, limite_de_credito, dt_nascimento)
            VALUES (@CPF, @nome, @email, @limite_de_credito, @dt_nascimento)
			SET @saida = 'Cliente inserido com sucesso'
        END
        ELSE
        BEGIN
            RAISERROR('CPF inv�lido ou j� existente na tabela.', 16, 1)
        END
    END
    ELSE IF @acao = 'U'
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM cliente WHERE CPF = @CPF)
        BEGIN
            UPDATE cliente
            SET nome = @nome, email = @email, limite_de_credito = @limite_de_credito, dt_nascimento = @dt_nascimento
            WHERE CPF = @CPF
			SET @saida = 'Cliente alterado com sucesso'
        END
        ELSE
        BEGIN
            RAISERROR('CPF n�o pode ser alterado.', 16, 1)
        END
    END
    ELSE IF @acao = 'D'
    BEGIN
        DELETE FROM cliente WHERE CPF = @CPF
		SET @saida = 'Cliente exclu�do com sucesso'
    END
    ELSE
    BEGIN
        RAISERROR('Opera��o inv�lida.', 16, 1)
    END
END

SELECT * FROM cliente



