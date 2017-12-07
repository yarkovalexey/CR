<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>CarShop</title>
            </head>
            <body>
                <h1 align="center">CarShop</h1>
                <table align="center" border="3" cellpadding="5" cellspacing="3">
                    <tr bgcolor="#e7e7e7">
                        <th>ID</th>
                        <th>Model</th>
                        <th>Country</th>
                        <th>Year</th>
                        <th>V</th>
                        <th>Price</th>
                    </tr>
                    <xsl:for-each select="CarShop/Car">
                        <tr>
                            <td><xsl:value-of select="ID"/></td>
                            <td><xsl:value-of select="Model"/></td>
                            <td><xsl:value-of select="Country"/></td>
                            <td><xsl:value-of select="Year"/></td>
                            <td><xsl:value-of select="V"/></td>
                            <td><xsl:value-of select="Price"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>