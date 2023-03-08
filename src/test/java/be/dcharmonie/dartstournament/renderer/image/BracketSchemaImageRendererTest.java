package be.dcharmonie.dartstournament.renderer.image;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.xhtmlrenderer.util.FSImageWriter;

import be.dcharmonie.dartstournament.core.Tournament;
import be.dcharmonie.dartstournament.renderer.image.layout.BracketSchemaPaperPrinter;

public class BracketSchemaImageRendererTest {

    @ParameterizedTest
    @ValueSource(ints = {8, 16, 32, 64})
    void testTournamentSchemas8(int numberOfPlayersKnockOutPhase) throws IOException {
        Tournament tournament = new Tournament(0, 0, numberOfPlayersKnockOutPhase);
        BracketSchemaPaperPrinter printer = new BracketSchemaPaperPrinter(tournament.getNumberOfPlayersKnockOutPhase(),
                tournament.getFirstRoundType().getRoundNumber());
        BracketSchemaImageRenderer drawer = new BracketSchemaImageRenderer(tournament);
        drawer.createSchema(printer);
        ByteArrayOutputStream byteArrayOutputStreamActual = new ByteArrayOutputStream();
        ImageIO.write(printer.getImage(), "png", byteArrayOutputStreamActual);

        Path resourceDirectory = Paths.get("src","test","resources", String.format("schema%s.png", numberOfPlayersKnockOutPhase));
        BufferedImage expectedImage = ImageIO.read(resourceDirectory.toFile());
        ByteArrayOutputStream byteArrayOutputStreamExpected = new ByteArrayOutputStream();
        ImageIO.write(expectedImage, "png", byteArrayOutputStreamExpected);

        assertThat(byteArrayOutputStreamActual.toByteArray()).isEqualTo(byteArrayOutputStreamExpected.toByteArray());
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(ints = {8, 16, 32, 64})
    void createNewExpectedSchemas(int numberOfPlayersKnockOutPhase) throws IOException {
        Tournament tournament = new Tournament(0, 0, numberOfPlayersKnockOutPhase);
        BracketSchemaPaperPrinter printer = new BracketSchemaPaperPrinter(tournament.getNumberOfPlayersKnockOutPhase(),
                tournament.getFirstRoundType().getRoundNumber());
        BracketSchemaImageRenderer drawer = new BracketSchemaImageRenderer(tournament);
        drawer.createSchema(printer);
        FSImageWriter imageWriter = new FSImageWriter();
        Path resourceDirectory = Paths.get("src","test","resources", String.format("schema%s.png", numberOfPlayersKnockOutPhase));
        imageWriter.write(printer.getImage(), resourceDirectory.toFile().getPath());
    }
}
